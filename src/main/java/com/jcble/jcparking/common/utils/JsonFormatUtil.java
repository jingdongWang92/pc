package com.jcble.jcparking.common.utils;
/**
 * 格式化输入工具类
 * @author Jingdong Wang
 * @Date  2016-02-14 
 *
 */
public final class JsonFormatUtil {

	/**
	 * 打印输入到控制台
	 * 
	 * @param jsonStr
	 * @author Jingdong Wang
	 * @Date 2016-02-14
	 */
	public static void printJson(String jsonStr) {
		System.out.println(formatJson(jsonStr));
	}
   
	/**
	 * 格式化
	 * @param jsonStr
	 * @return
	 * @author Jingdong Wang
	 * @Date 2016-02-14
	 */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

	/**
	 * 添加space
	 * 
	 * @param sb
	 * @param indent
	 * @author Jingdong Wang
	 * @Date 2016-02-14
	 */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append("  ");
        }
    }
}