package cn.com.onlinetool.common.util;


/**
 * @author choice
 * @description:
 * @date 2018/10/21 22:29
 */
public class PoiExcelUtil {
//    /**
//     * serialVersionUID
//     */
//    private static final long serialVersionUID = 1L;
//
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(JxlExcelUtil.class);
//    //设置Excel读取最大行数
//    private static final int MAX_ROW = 20000;
//
//    /**
//     * getMap:(将传进来的表头和表头对应的属性存进Map集合，表头字段为key,属性为value)
//     *
//     * @author likaixuan,wolfgy
//     * @version 1.1 2017年9月18日
//     * @param 把传进指定格式的字符串解析到Map中
//     *            形如: String keyValue = "手机名称:phoneName,颜色:color,售价:price";
//     * @return Map<String, String> 转换好的Map集合
//     * @since JDK 1.7
//     */
//    public static Map<String, String> getMap(String keyValue) {
//        Map<String, String> map = new HashMap<>();
//        if (keyValue != null) {
//            String[] str = keyValue.split(",");
//            for (String element : str) {
//                String[] str2 = element.split(":");
//                map.put(str2[0], str2[1]);
//            }
//        }
//        return map;
//    }
//
//    /**
//     * readExcel:根据传进来的map集合读取Excel以及model读取Excel文件
//     *
//     * @author likaixuan,wolfgy
//     * @version 1.1 2017年9月18日
//     * @param fileName
//     *            Excel文件名
//     * @param inputStream 输入流
//     * @param map
//     *            表头和属性的Map集合,其中Map中Key为Excel列的名称，Value为反射类的属性
//     * @param classPath
//     *            需要映射的model的路径
//     * @param rowNumIndex
//     *               表头所在行数(从1开始，即第一行对应行数1)
//     * @return List<T> 读取到的数据集合
//     * @throws Exception
//     * @since JDK 1.7
//     */
//    @SuppressWarnings({ "resource", "unchecked" })
//    public static <T> List<T> readExcel(String fileName, InputStream inputStream, Map<String,String>  map,
//                                        String classPath, int rowNumIndex) throws Exception {
//        // 返回表头字段名和属性字段名Map集合中键的集合(Excel列的名称集合)
//        Set<String> keySet = map.keySet();
//
//        //反射用
//        Class<?> demo = null;
//        Object obj = null;
//        List<Object> list = new ArrayList<Object>();
//        demo = Class.forName(classPath);
//        //获取文件名后缀判断文件类型
//        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,
//                fileName.length());
//
//        //根据文件类型及文件输入流新建工作簿对象
//        Workbook wb = null;
//        if (fileType.equals("xls")) {
//            wb = new HSSFWorkbook(inputStream);
//        } else if (fileType.equals("xlsx")) {
//            wb = new XSSFWorkbook(inputStream);
//        } else {
//            LOGGER.error("您输入的excel格式不正确");
//            throw new Exception("您输入的excel格式不正确");
//        }
//        // 遍历每个Sheet表
//        for (int sheetNum = 0; sheetNum < 1; sheetNum++) {
//            // 表头成功读取标志位。当表头成功读取后，rowNum_x值为表头实际行数
//            int rowNum_x = -1;
//            // 存放每一个field字段对应所在的列的序号
//            Map<String, Integer> cellmap = new HashMap<String, Integer>();
//            // 存放所有的表头字段信息
//            List<String> headlist = new ArrayList<>();
//            // 获取当前Sheet表
//            Sheet hssfSheet = wb.getSheetAt(sheetNum);
//
//            //设置默认最大行数,当超出最大行数时返回异常
//            if(hssfSheet != null && hssfSheet.getLastRowNum()>MAX_ROW){
//                throw new Exception("Excel 数据超过20000行,请检查是否有空行,或分批导入");
//            }
//
//            // 遍历Excel中的每一行
//            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
//                // 当表头成功读取标志位rowNum_x为-1时，说明还未开始读取数据。此时，如果传值指定读取其实行，就从指定行寻找，否则自动寻找。
//                if (rowNum_x == -1) {
//                    //判断指定行是否为空
//                    Row hssfRow = hssfSheet.getRow(rowNumIndex);
//                    if (hssfRow == null) {
//                        throw new RuntimeException("指定的行为空，请检查");
//                    }
//                    //设置当前行为指定行
//                    rowNum = rowNumIndex - 1;
//                }
//
//                //获取当前行
//                Row hssfRow = hssfSheet.getRow(rowNum);
//                //当前行为空时，跳出本次循环进入下一行
//                if (hssfRow == null) continue;
//
//                //当前行数据为空时，跳出本次循环进入下一行
//                boolean flag = false;
//                for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
//                    if (hssfRow.getCell(i) != null && !("").equals(hssfRow.getCell(i).toString().trim())) {
//                        flag = true;
//                    }
//                }
//                if (!flag) continue;
//
//
//                //获取表头内容
//                if (rowNum_x == -1) {
//                    // 循环列Cell
//                    for (int cellNum = 0; cellNum <= hssfRow
//                            .getLastCellNum(); cellNum++) {
//
//                        Cell hssfCell = hssfRow.getCell(cellNum);
//                        //当前cell为空时，跳出本次循环，进入下一列。
//                        if (hssfCell == null) {
//                            continue;
//                        }
//                        //获取当前cell的值(String类型)
//                        String tempCellValue = hssfSheet.getRow(rowNum)
//                                .getCell(cellNum).getStringCellValue();
//                        //去除空格,空格ASCII码为160
//                        tempCellValue = StringUtils.remove(tempCellValue,
//                                (char) 160);
//                        tempCellValue = tempCellValue.trim();
//                        //将表头内容放入集合
//                        headlist.add(tempCellValue);
//
//                        //遍历表头字段名和属性字段名Map集合中键的集合(Excel列的名称集合)
//                        Iterator<String> it = keySet.iterator();
//                        while (it.hasNext()) {
//                            Object key = it.next();
//                            if (StringUtils.isNotBlank(tempCellValue)
//                                    && StringUtils.equals(tempCellValue,
//                                    key.toString())) {
//                                //将rowNum_x设为实际的表头行数
//                                rowNum_x = rowNum;
//                                //获取表头每一个field字段对应所在的列的序号
//                                cellmap.put(map.get(key).toString(), cellNum);
//                            }
//                        }
//                        //当rowNum_x为-1时，说明没有在表头找到对应的字段或者对应字段行上面含有不为空白的行字段，返回异常。
//                        if (rowNum_x == -1) {
//                            LOGGER.error("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
//                            throw new Exception("没有找到对应的字段或者对应字段行上面含有不为空白的行字段");
//                        }
//                    }
//
//                } else {
//                    //实例化反射类对象
//                    obj = demo.newInstance();
//                    //遍历并取出所需要的每个属性值
//                    Iterator<String> it = keySet.iterator();
//                    while (it.hasNext()) {
//                        //Excel列名
//                        Object key = it.next();
//                        //获取属性对应列数
//                        Integer cellNum_x = cellmap
//                                .get(map.get(key).toString());
//                        //当属性对应列为空时，结束本次循环，进入下次循环，继续获取其他属性值
//                        if (cellNum_x == null || hssfRow.getCell(cellNum_x) == null) {
//                            continue;
//                        }
//                        //得到属性名
//                        String attrName = map.get(key).toString();
//                        //得到属性类型
//                        Class<?> attrType = BeanUtils.findPropertyType(attrName,
//                                new Class[] { obj.getClass() });
//                        //得到属性值
//                        Cell cell = hssfRow.getCell(cellNum_x);
//                        Object val = getValue(cell, obj, attrName, attrType, rowNum, cellNum_x,
//                                key);
//                        setter(obj, attrName, val, attrType, rowNum, cellNum_x, key);
//
//                    }
//                    //将实例化好并设置完属性的对象放入要返回的list中
//                    list.add(obj);
//                }
//
//            }
//        }
//        wb.close();
//        inputStream.close();
//
//        return (List<T>) list;
//    }
//
//
//    /**
//     *
//     * <p>
//     * Description:setter(反射set方法给属性赋值)<br />
//     * </p>
//     * @author likaixuan,wolfgy
//     * @version 1.1 2017年9月18日
//     * @param obj 反射类对象
//     * @param attrName 属性名
//     * @param attrValue 属性值
//     * @param attrType 属性类型
//     * @param row 当前数据在Excel中的具体行数
//     * @param column 当前数据在Excel中的具体列数
//     * @param key 当前数据对应的Excel列名
//     * @since JDK 1.7
//     * @throws Exception
//     * void
//     */
//    public static void setter(Object obj, String attrName, Object attrValue,
//                              Class<?> attrType, int row, int column, Object key) throws Exception {
//        try {
//            //获取反射的方法名
//            Method method = obj.getClass().getMethod(
//                    "set" + StringUtil.toUpperCaseFirstOne(attrName), attrType);
//            //进行反射
//            method.invoke(obj, attrValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error("第" + (row + 1) + " 行  " + (column + 1) + "列   属性：" + key
//                    + " 赋值异常  " + e.getStackTrace());
//            throw new Exception("第" + (row + 1) + " 行  " + (column + 1) + "列   属性："
//                    + key + " 赋值异常  ");
//        }
//
//    }
//
//    /**
//     * <p>
//     * Description:getter(反射get方法得到属性值)<br />
//     * </p>
//     * @author likaixuan,wolfgy
//     * @version 1.1 2017年9月18日
//     * @param obj
//     *            反射类对象
//     * @param attrName
//     *            属性名
//     * @throws Exception
//     * @since JDK 1.7
//     */
//    public static Object getter(Object obj, String attrName)
//            throws Exception {
//        try {
//            //获取反射的方法名
//            Method method = obj.getClass().getMethod("get" + StringUtil.toUpperCaseFirstOne(attrName));
//            Object value = new Object();
//            //进行反射并获取返回值
//            value = method.invoke(obj);
//            return value;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//
//    /**
//     *
//     * <p>
//     * Description:读取当前单元格的值<br />
//     * </p>
//     * @author likaixuan,wolfgy
//     * @version 1.1 2017年9月18日
//     * @param cell 单元格对象
//     * @param obj 反射类对象
//     * @param attrName 属性名
//     * @param attrType 属性类型
//     * @param row 当前数据在Excel中的具体行数
//     * @param col 当前数据在Excel中的具体列数
//     * @param key 当前数据对应的Excel列名
//     * @throws Exception
//     * @since JDK 1.7
//     * @return val 当前单元格的值
//     */
//    public static Object getValue(Cell cell, Object obj, String attrName,
//                                  Class<?> attrType, int row, int column, Object key) throws Exception {
//        //新建当前单元格值对象
//        Object val = null;
//        //判断当前单元格数据类型并取值
//        if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
//            val = cell.getBooleanCellValue();
//
//        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
//            if (DateUtil.isCellDateFormatted(cell)) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    if (attrType == String.class) {
//                        val = sdf.format(DateUtil
//                                .getJavaDate(cell.getNumericCellValue()));
//                    } else {
//                        val = StringUtil.dateConvertFormat(
//                                sdf.format(DateUtil.getJavaDate(
//                                        cell.getNumericCellValue())));
//                    }
//                } catch (ParseException e) {
//                    LOGGER.error("日期格式转换错误");
//                    throw new Exception("第" + (row + 1) + " 行  " + (column + 1)
//                            + "列   属性：" + key + " 日期格式转换错误  ");
//                }
//            } else {
//                if (attrType == String.class) {
//                    cell.setCellType(CellType.STRING);
//                    val = cell.getStringCellValue();
//                } else if (attrType == BigDecimal.class) {
//                    val = new BigDecimal(cell.getNumericCellValue());
//                } else if (attrType == long.class) {
//                    val = (long) cell.getNumericCellValue();
//                } else if (attrType == Double.class) {
//                    val = cell.getNumericCellValue();
//                } else if (attrType == Float.class) {
//                    val = (float) cell.getNumericCellValue();
//                } else if (attrType == int.class || attrType == Integer.class) {
//                    val = (int) cell.getNumericCellValue();
//                } else if (attrType == Short.class) {
//                    val = (short) cell.getNumericCellValue();
//                } else {
//                    val = cell.getNumericCellValue();
//                }
//            }
//
//        } else if (cell.getCellTypeEnum() == CellType.STRING) {
//            val = cell.getStringCellValue();
//        }
//        return val;
//
//    }
//
//    /**
//     *
//     * <p>
//     * Description:Excel导出<br />
//     * </p>
//     * @author likaixuan,wolfgy
//     * @version 1.1 2017年9月18日
//     * @param titleText 标题栏内容
//     * @param out 输出流
//     * @param map  表头和属性的Map集合,其中Map中Key为Excel列的名称，Value为反射类的属性
//     * @param list 要输出的对象集合
//     * @param classPath 需要映射的model的路径
//     * @param titleStyle 标题栏样式。若为null则直接使用默认样式
//     * @param headStyle 表头样式。若为null则直接使用默认样式
//     * @param dataStyle 数据行样式。若为null则直接使用默认样式
//     * @throws Exception
//     * @since JDK 1.7
//     * void
//     */
//    public static void exportExcel(String titleText, OutputStream out, Map<String,String> map, List<?> list, String classPath, HSSFCellStyle titleStyle, HSSFCellStyle headStyle, HSSFCellStyle dataStyle) throws Exception {
//
//        //创建单元格并设置单元格内容
//        Set<String> keySet = map.keySet();// 返回键的集合
//        Iterator<String> it = keySet.iterator();
//        // 创建HSSFWorkbook对象(excel的文档对象)
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 建立新的sheet对象（excel的表单）
//        HSSFSheet sheet = workbook.createSheet("数据导出");
//
//        // 设置默认列宽为15
//        sheet.setDefaultColumnWidth(15);
//        // 合并标题栏单元格
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, keySet.size() - 1));
//        // 当传入的标题栏样式为空时，创建默认标题栏样式
//        if (titleStyle == null) {
//            HSSFCellStyle style = workbook.createCellStyle();
//            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex());
//            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            style.setBorderBottom(BorderStyle.THIN);
//            style.setBorderLeft(BorderStyle.THIN);
//            style.setBorderRight(BorderStyle.THIN);
//            style.setBorderTop(BorderStyle.THIN);
//            style.setAlignment(HorizontalAlignment.CENTER);
//            style.setVerticalAlignment(VerticalAlignment.CENTER);
//            HSSFFont font = workbook.createFont();
//            font.setColor(HSSFColor.HSSFColorPredefined.VIOLET.getIndex());
//            font.setFontHeightInPoints((short) 18);
//            style.setFont(font);
//            titleStyle = style;
//        }
//        // 当传入的表头样式为空时，创建默认表头样式
//        if (headStyle == null) {
//            HSSFCellStyle style2 = workbook.createCellStyle();
//            style2.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
//            style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            style2.setBorderBottom(BorderStyle.THIN);
//            style2.setBorderLeft(BorderStyle.THIN);
//            style2.setBorderRight(BorderStyle.THIN);
//            style2.setBorderTop(BorderStyle.THIN);
//            style2.setAlignment(HorizontalAlignment.CENTER);
//            style2.setVerticalAlignment(VerticalAlignment.CENTER);
//            HSSFFont font2 = workbook.createFont();
//            font2.setFontHeightInPoints((short) 12);
//            style2.setFont(font2);
//            headStyle = style2;
//        }
//        // 当传入的数据行样式为空时，创建默认数据行样式
//        if (dataStyle == null) {
//            HSSFCellStyle style3 = workbook.createCellStyle();
//            style3.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
//            style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            style3.setBorderBottom(BorderStyle.THIN);
//            style3.setBorderLeft(BorderStyle.THIN);
//            style3.setBorderRight(BorderStyle.THIN);
//            style3.setBorderTop(BorderStyle.THIN);
//            style3.setAlignment(HorizontalAlignment.CENTER);
//            style3.setVerticalAlignment(VerticalAlignment.CENTER);
//            dataStyle = style3;
//        }
//
//        // 创建行、单元格对象
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        // 写入标题行
//        row = sheet.createRow(0);
//        row.setHeightInPoints(25);
//        cell = row.createCell(0);
//        cell.setCellStyle(titleStyle);
//        HSSFRichTextString textTitle = new HSSFRichTextString(titleText);
//        cell.setCellValue(textTitle);
//
//
//        //写入表头
//        row = sheet.createRow(1);//参数为行索引(excel的行)，可以是0～65535之间的任何一个
//        Map<String,String> attrMap = new HashMap<>();
//        int index = 0;
//        while(it.hasNext()){
//            String key = it.next().toString();
//            cell = row.createCell(index);
//            cell.setCellValue(key);
//            cell.setCellStyle(headStyle);
//            attrMap.put(Integer.toString(index++), map.get(key).toString());
//        }
//        //写入数据行
//        for(int i=2;i<list.size();i++){
//            row = sheet.createRow(i);
//            for(int j=0;j<map.size();j++){
//                //调用getter获取要写入单元格的数据值
//                Object value = getter(list.get(i), attrMap.get(Integer.toString(j)));
//                cell = row.createCell(j);
//                cell.setCellValue(value.toString());
//                cell.setCellStyle(dataStyle);
//            }
//        }
//
//        // 输出Excel文件
//        try {
//            workbook.write(out);
//            out.flush();
//            out.close();
//            workbook.close();
//            LOGGER.info("导出成功!");
//        } catch (IOException e) {
//            LOGGER.info("IOException!导出失败！");
//            e.printStackTrace();
//        }
//
//    }
}
