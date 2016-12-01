package com.fosun.fc.projects.creepers.getResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import com.fosun.fc.projects.creepers.constant.BaseConstant;
import com.fosun.fc.projects.creepers.utils.CommonMethodUtils;
import com.fosun.fc.projects.creepers.utils.TranslateMethodUtil;

/**
 * 
 * <p>
 * 生成DAO.java     pipeLine.java
 * </p>
 * 
 * @author MaXin
 * @since 2016-7-14 15:37:00
 * @see
 */
public class GenerDAO {

    public static void main(String[] args) {
        String filePaht = "D:/tableColumn/now/";
        readAndWriteFile(filePaht);
        readFileGenerInsertCode(filePaht);
    }

    public static String addPackage() {
        return "package com.fosun.fc.projects.creepers.dao;\n\n";
    }

    public static String addImportRepository() {
        return "import org.springframework.data.jpa.repository.JpaRepository;\n"
                + "import org.springframework.data.jpa.repository.JpaSpecificationExecutor;\n\n";
    }

    public static String addImportEntity(String entityName) {
        return "import com.fosun.fc.projects.creepers.entity." + entityName + ";\n";
    }

    public static String addDescription(String desc) {
        StringBuffer result = new StringBuffer();
        result.append("\n");
        result.append("/**").append("\n");
        result.append("*").append("\n");
        result.append("* <p>").append("\n");
        result.append("* description:").append("\n");
        result.append("* ").append(desc).append("\n");
        result.append("* <p>").append("\n");
        result.append("* @author MaXin").append("\n");
        result.append("* @since ").append(
                CommonMethodUtils.dateFormat(new Date(), BaseConstant.DateFormatPatternType.TYPE_ONE.getValue()))
                .append("\n");
        result.append("* @see").append("\n");
        result.append("*/").append("\n").append("\n");
        return result.toString();
    }

    public static String addContent(String className, String entityName) {
        StringBuffer result = new StringBuffer();
        result.append("public interface ").append(className);
        result.append(" extends JpaRepository<").append(entityName).append(", Long>, JpaSpecificationExecutor<")
                .append(entityName).append("> {\n");
        result.append("}");
        return result.toString();
    }

    public static void readFileGenerInsertCode(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            String[] result = file.list();
            for (String string : result) {
                String entityName = TranslateMethodUtil
                        .firstUpperOtherLower(TranslateMethodUtil.deleteFileSuffix(string));
                String className = TranslateMethodUtil.deleteFirstWord(entityName) + "Dao";
                // generImportDao(className);
                // generImportEntity(entityName);
                // generTableDaoEntity(string, className, entityName);
                // generAutoDao(className);
                generInsertDB(TranslateMethodUtil.deleteFileSuffix(string),
                        TranslateMethodUtil.firstWordToLower(className), entityName);
            }
        }
    }

    public static void readAndWriteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            String[] result = file.list();
            for (String string : result) {
                StringBuffer exportString = new StringBuffer();
                String entityName = TranslateMethodUtil
                        .firstUpperOtherLower(TranslateMethodUtil.deleteFileSuffix(string));
                String className = TranslateMethodUtil.deleteFirstWord(entityName) + "Dao";
                generInsertDB(TranslateMethodUtil.deleteFileSuffix(string),
                        TranslateMethodUtil.firstWordToLower(className), entityName);
                String inputFilePath = filePath + string;
                String outPutFilePath = BaseConstant.JavaFilePath.CREEPERS_DAO_FILE_PATH.getValue() + className
                        + BaseConstant.FileSuffix.JAVA.getValue();
                try {
                    @SuppressWarnings("resource")
                    String desc = new BufferedReader(new FileReader(inputFilePath)).readLine();
                    exportString.append(addPackage());
                    exportString.append(addImportRepository());
                    exportString.append(addImportEntity(entityName));
                    exportString.append(addDescription(desc));
                    exportString.append(addContent(className, entityName));
                    System.out.println("==========>>>开始生成：" + outPutFilePath);
                    PrintWriter print = new PrintWriter(new FileWriter(new File(outPutFilePath)));
                    print.write(exportString.toString());
                    print.flush();
                    print.close();
                    System.out.println("==========>>>完成！" + outPutFilePath);
                } catch (IOException e) {
                    System.out.println("==========>>>生成文件错误！" + outPutFilePath);
                    e.printStackTrace();
                }
            }
        }
    }

    public static void generImportDao(String daoName) {
        System.out.println("import com.fosun.fc.projects.creepers.dao." + daoName + ";");
    }

    public static void generImportEntity(String entityName) {
        System.out.println("import com.fosun.fc.projects.creepers.entity." + entityName + ";");
    }

    public static void generTableDaoEntity(String fileName, String className, String entityName) {
        System.out.println(TranslateMethodUtil.deleteFileSuffix(fileName) + " "
                + TranslateMethodUtil.firstWordToLower(className) + " " + entityName);
    }

    public static void generAutoDao(String className) {
        System.out.println("@Autowired(required = true)\nprivate " + className + " "
                + TranslateMethodUtil.firstWordToLower(className) + ";\n\n");
    }

    // if
    // (tableName.getMapKey().equals(CreepersConstant.TableNamesBusinessInfo.T_CREEPERS_ENT_BASIC.getMapKey()))
    // {
    // if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {
    // continue;
    // }
    // if (tableName.isList()) {
    // List<TCreepersEntBasic> entityList =
    // CommonMethodUtils.mapList(resultItems.get(tableName.getMapKey()), new
    // TCreepersEntBasic());
    // for (TCreepersEntBasic entity : entityList) {
    // CommonMethodUtils.setByDT(entity);
    // }
    // logger.info("step: ======>> entry saveList");
    // creepersEntBasicDao.save(entityList);
    // logger.info("step: ======>> saveList succeed!");
    // } else {
    // TCreepersEntBasic entity = new TCreepersEntBasic();
    // CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()),
    // entity);
    // CommonMethodUtils.setByDT(entity);
    // logger.info("step: ======>> entry saveEntity");
    // creepersEntBasicDao.save(entity);
    // logger.info("step: ======>> saveEntity succeed!");
    // }
    // } else

    public static void generInsertDB(String tableName, String dao, String entity) {
        StringBuffer result = new StringBuffer();
        result.append("if(tableName.getMapKey().equals(CreepersConstant.TableNamesBusinessInfo.").append(tableName)
                .append(".getMapKey())){").append("\n");
        result.append("if (CommonMethodUtils.isEmpty(resultItems.get(tableName.getMapKey()))) {").append("\n");
        result.append("continue;").append("\n");
        result.append("}").append("\n");
        result.append("if (tableName.isList()) {").append("\n");
        result.append("List<").append(entity).append("> entityList =")
                .append("CommonMethodUtils.mapList(resultItems.get(tableName.getMapKey()), new ").append(entity)
                .append("());").append("\n");
        result.append("for (").append(entity).append(" entity : entityList) {").append("\n");
        result.append("CommonMethodUtils.setByDT(entity);").append("\n");
        result.append("}").append("\n");
        result.append("logger.info(\"step: ======>> entry saveList\");").append("\n");
        result.append(dao).append(".save(entityList);").append("\n");
        result.append("logger.info(\"step: ======>> saveList succeed!\");").append("\n");
        result.append("} else {").append("\n");
        result.append(entity).append(" entity = new ").append(entity).append("();").append("\n");
        result.append("CommonMethodUtils.mapTransEntity(resultItems.get(tableName.getMapKey()),entity);").append("\n");
        result.append("CommonMethodUtils.setByDT(entity);").append("\n");
        result.append("logger.info(\"step: ======>> entry saveEntity\");").append("\n");
        result.append(dao).append(".save(entity);").append("\n");
        result.append("logger.info(\"step: ======>> saveEntity succeed!\");").append("\n");
        result.append("}").append("\n");
        result.append("} else ").append("\n");
        System.out.println(result.toString());
    }
}
