import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil
import com.intellij.psi.codeStyle.NameUtil


/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

typeMapping = [
        (~/(?i)int/)                         : "Integer",
        (~/(?i)bigint/)                      : "Long",
        (~/(?i)float|double|decimal|real/)   : "BigDecimal",
        (~/(?i)time|date|datetime|timestamp/): "LocalDateTime",
        (~/(?i)/)                            : "String"
]

feildTypeMap = [
        "BigDecimal"   : "java.math.BigDecimal",
        "LocalDateTime": "java.time.LocalDateTime"
]
/** 导入包列表 **/
pacakgeImportList = new HashSet<String>()
/** 插入语句忽略字段 **/
insertIgnoreList = ['id', 'server_create_time', 'server_update_time']
/** 更新语句忽略字段 **/
updateIgnoreList = ['id', 'creator_id', 'creator_name', 'server_create_time', 'server_update_time']
/** 表名-->类名转换前缀忽略字段 **/
classNameIgnorePrefixList = ['sys_']


FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each {
        def packageName = dirConvert2PackageName(dir)
        def className = tableName2JavaName(it.getName(), true)
        def fieldArray = calcFields(it)
        generateEntity(it, dir, fieldArray, className, packageName, false)
        generateMapperDao(it, dir, fieldArray, className, packageName, false)
        generateMapperXml(it, dir, fieldArray, className, packageName, false)
    }
}


/**
 * 选择的文件加名称转换为包名
 * @param dir 选择的文件夹路径名
 * @return 转换后的包名
 */
def dirConvert2PackageName(dir) {
    def packageName = dir.absolutePath.toString()
            .split("/src/main/java/")[1]
            .replace('/', '.')
    return packageName
}

/**
 * 下划线表名转化为Java标准命名
 * @param tableName
 */
def tableName2JavaName(tableName, capitalize) {
    for (prefix in classNameIgnorePrefixList) {
        if (tableName.startsWith(prefix)) {
            tableName = tableName.replace(prefix, "")
        }
    }
    def s = NameUtil.splitNameIntoWords(tableName)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        if (feildTypeMap.containsKey(typeStr)) {
            pacakgeImportList << feildTypeMap.get(typeStr)
        }
        fields += [[
                           name   : tableName2JavaName(col.getName(), false),
                           field  : col.getName(),
                           type   : typeStr,
                           annos  : "",
                           comment: col.getComment()
                   ]]
    }
}

def entityClassName(baseName) {
    baseName + "Entity"
}

def daoClassName(baseName) {
    baseName + "Dao"
}

def entityPackageName(basePackageName) {
    basePackageName + ".entity"
}

def daoPackageName(basePackageName) {
    basePackageName + ".dao"
}

/** 做一次目录转换 **/
def convertDir(dir, child) {
    def folder = new File(dir, child)
    if (!folder.exists()) {
        folder.mkdirs()
    }
    return folder
}

def entityDir(dir) {
    return convertDir(dir, "entity")
}

def daoDir(dir) {
    return convertDir(dir, "dao")
}

def xmlDir(dir){
    /** 没有单独使用java拆分避免应用前置存在该路径故使用/src/main/java拆分后补充/src/main **/
    def mappingBase = new File(dir.absolutePath.toString()
            .split("/src/main/java/")[0]+"/src/main/resources/mapping/dao")
    if(!mappingBase.exists()){
        mappingBase.mkdirs()
    }
    return mappingBase
}


def getIdType(fields) {
    return fields.find { it.name == 'id' }.type
}

/** 生成 数据库表映射的entity 文件 **/
def generateEntity(table, dir, fields, className, packageName, refresh) {
    def entityFile = new File(entityDir(dir), entityClassName(className) + ".java")
    if(refresh || !entityFile.exists()){
        entityFile.withPrintWriter { out ->
                    generateEntityContent(out, table, className,
                            fields, entityPackageName(packageName))
                }
    }
}

/** 生成 mybatis dao层的 Java 文件 **/
def generateMapperDao(table, dir, fields, className, packageName, refresh) {
    def daoFile = new File(daoDir(dir), daoClassName(className) + ".java")
    if(refresh || !daoFile.exists()){
        daoFile.withPrintWriter { out ->
                    generateMapperDaoContent(out, table, className, fields,
                            entityPackageName(packageName), daoPackageName(packageName))
                }
    }

}

/** 生成 mybatis dao层的 xml 文件 **/
def generateMapperXml(table, dir, fields, className, packageName, refresh) {
    def xmlFile = new File(xmlDir(dir), daoClassName(className) + ".xml")
    if(refresh || !xmlFile.exists()){
        xmlFile.withPrintWriter { out ->
                    generateMapperXmlContent(out, table, className, fields,
                            daoPackageName(packageName), entityPackageName(packageName))
                }
    }
}

/** 生成实体内容 **/
def generateEntityContent(out, table, baseName, fields, entityPackage) {
    def content = """
package $entityPackage;

import lombok.Data;

${pacakgeImportList.collect { pkg -> "import $pkg;" }.join("\n")}

/**
 * ${null == table.getComment() ? "" : table.getComment()}数据库映射类（表${table.getName()}）
 *
 * @author generator
 * @since 1.8
 */
@Data
public class ${entityClassName(baseName)} {
${
        fields.collect {
            """
    /**
     * ${it.comment}
     */
    private ${it.type} ${it.name};
"""
        }.join("")
    }
}
"""
    out.println content
}

/** 生成Dao内容 **/
def generateMapperDaoContent(out, table, baseName, fields, entityPackage, daoPackage) {
    def idTypeStr = getIdType(fields)
    def content = """
package ${daoPackage};

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ${entityPackage}.${entityClassName(baseName)};

import java.util.List;

/**
 * ${null == table.getComment() ? "" : table.getComment()}数据库操作类（表${table.getName()}）
 *
 * @author generator
 * @since 1.8
 */
@Mapper
public interface ${daoClassName(baseName)} {

    /**
     * 创建${null == table.getComment() ? "表数据" : table.getComment()}
     *
     * @param entity ${null == table.getComment() ? "表数据" : table.getComment()}
     * @return 受影响行数
     */
    int insert(${entityClassName(baseName)} entity);

    /**
     * 批量创建${null == table.getComment() ? "表数据" : table.getComment()}
     *
     * @param list ${null == table.getComment() ? "表数据" : table.getComment()}列表
     * @return 受影响行数
     */
    int batchInsert(@Param("list") List<${entityClassName(baseName)}> list);

    /**
     * 更新${null == table.getComment() ? "表数据" : table.getComment()}，并发高的情况下应避免使用该方法修改数据，或者应尽量使用独占更新
     *
     * @param entity ${null == table.getComment() ? "表数据" : table.getComment()}
     * @return 受影响行数
     */
    int updateById(${entityClassName(baseName)} entity);

    /**
     * 更新${null == table.getComment() ? "表数据" : table.getComment()}不为空字段. 需关注有默认值的字段
     *
     * @param entity ${null == table.getComment() ? "表数据" : table.getComment()}
     * @return 受影响行数
     */
    int updateByIdSelective(${entityClassName(baseName)} entity);

    /**
     * 通过主键删除${null == table.getComment() ? "表数据" : table.getComment()}
     *
     * @param id 删除的主键
     */
    int deleteById(@Param("id") ${idTypeStr} id);

    /**
     * 独占获取指定${null == table.getComment() ? "表数据" : table.getComment()}
     *
     * @param id 需要查找记录的主键
     * @return ${table.getComment()}
     */
    ${entityClassName(baseName)} lockById(@Param("id") ${idTypeStr} id);

    /**
     * 获取${null == table.getComment() ? "表数据" : table.getComment()}
     *
     * @param id 需要查找记录的主键
     * @return ${table.getComment()}
     */
    ${entityClassName(baseName)} findById(@Param("id") ${idTypeStr} id);

    /**
     * 获取满足条件的所有${null == table.getComment() ? "表数据" : table.getComment()}
     *
     * @param entity 不为空字段作为查询条件查询
     * @return ${table.getComment()}列表
     */
    List<${entityClassName(baseName)}> findAll(${entityClassName(baseName)} entity);

}
"""
    out.println content
}

/** 生成XML内容 **/
def generateMapperXmlContent(out, table, baseName, fields, daoPackage, entityPackage) {
    List insertList = fields.findAll { !insertIgnoreList.contains(it.field) }
    List updateList = fields.findAll { !updateIgnoreList.contains(it.field) }

    def content = """<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${daoPackage + '.' + daoClassName(baseName)}">

    <!-- @formatter:off -->
    <sql id="BasicColumns">${
        fields.withIndex().collect { item, idx ->
            "\n\t\t\${alias}.${item.field}"
        }.join(',')
    }
    </sql>

    <sql id="columns">${
        fields.withIndex().collect { item, idx ->
            "\n\t\t${item.field}"
        }.join(',')
    }
    </sql>
    <!-- @formatter:on -->




    <insert id="insert" useGeneratedKeys="true" keyProperty="id" parameterType="${entityPackage + '.' + entityClassName(baseName)}">
        INSERT INTO ${table.name}
        (${
        insertList.withIndex().collect { item, idx ->
            "${item.field}${(insertList.size() == idx + 1) ? '' : ", ${idx % 5 == 4 ? '\n\t\t ' : ''}"}"
        }.join('')
    })
        VALUES (${
        insertList.withIndex().collect { item, idx ->
            "#{${item.name}}${(insertList.size() == idx + 1) ? '' : ", ${idx % 5 == 4 ? '\n\t\t\t\t' : ''}"}"
        }.join('')
    })
    </insert>

    <insert id="batchInsert" useGeneratedKeys="true" keyProperty="id" parameterType="${entityPackage + '.' + entityClassName(baseName)}">
        INSERT INTO ${table.name}
        (${
        insertList.withIndex().collect { item, idx ->
            "${item.field}${(insertList.size() == idx + 1) ? '' : ", ${idx % 5 == 4 ? '\n\t\t ' : ''}"}"
        }.join('')
    })
        VALUES
        <foreach collection="list" item="item" separator=",">
            (${
        insertList.withIndex().collect { item, idx ->
            "#{item.${item.name}}${(insertList.size() == idx + 1) ? '' : ", ${idx % 5 == 4 ? '\n\t\t\t' : ''}"}"
        }.join('')
    })
        </foreach>
    </insert>

    <update id="updateById" parameterType="${entityPackage + '.' + entityClassName(baseName)}">
        UPDATE ${table.name} SET ${
        updateList.withIndex().collect { item, idx ->
            """
        ${item.field} = #{${item.name}}"""
        }.join(",")
    }
        WHERE id = #{id}
    </update>

    <update id="updateByIdSelective" parameterType="${entityPackage + '.' + entityClassName(baseName)}">
        UPDATE ${table.name}
        <set>${
        updateList.collect {
            """
            <if test="${it.name} != null">
                ${it.field} = #{${it.name}},
            </if>"""
        }.join('')
    }
        </set>
        WHERE id = #{id}
    </update>

    <delete id="deleteById">
        DELETE
        FROM ${table.name}
        WHERE id = #{id}
    </delete>

    <select id="lockById" resultType="${entityPackage + '.' + entityClassName(baseName)}">
        SELECT
        <include refid="BasicColumns">
            <property name="alias" value="t"/>
        </include>
        FROM ${table.name} t
        WHERE id = #{id}
        FOR UPDATE
    </select>

    <select id="findById" resultType="${entityPackage + '.' + entityClassName(baseName)}">
        SELECT
        <include refid="BasicColumns">
            <property name="alias" value="t"/>
        </include>
        FROM ${table.name} t
        WHERE id = #{id}
    </select>

    <select id="findAll" resultType="${entityPackage + '.' + entityClassName(baseName)}">
        SELECT
        <include refid="BasicColumns">
            <property name="alias" value="t"/>
        </include>
        FROM ${table.name} t
        <where>${
        fields.collect {
            """
            <if test="${it.name} != null">
                AND t.${it.field} = #{${it.name}}
            </if>"""
        }.join('')
    }
        </where>
    </select>

</mapper>
"""
    out.println content
}