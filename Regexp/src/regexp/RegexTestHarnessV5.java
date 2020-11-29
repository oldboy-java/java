package regexp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarnessV5 {

    public static void main(String[] args) {
        reg4();
    }

    /**
     *  ͨ���������������ڣ���ȡ�ꡢ�¡���
     *  group(0)��ʾƥ��������ַ���
     *  group(1)��ʾ��1���Ӵ�
     *  group(2)��ʾ��2���Ӵ�
     */
    public static void reg2(){
        String s = "2014-09-23";
        // ����patten����
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        // ��ȡƥ����
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            System.err.println("��ƥ���ַ���="+matcher.group(0));
            System.out.println(matcher.group(1)+"&&"+matcher.group(2)+"&&"+matcher.group(3));
        }
    }

    /**
     *  ͨ��������� + �������ã�\\ ����ʹ��$ ���з������ã�
     *  group(0)��ʾƥ��������ַ���
     *  group(1)��ʾ��1���Ӵ�
     *  group(2)��ʾ��2���Ӵ�
     */
    public static void reg3(){
        String s = "2014-09-23";

        // ��������(\\d{4})\\1-\\d{2}-\\d{2}������\\1��ת���ˣ�������ƥ�䵽�ĵ�һ�����鼴2014
        // ����Ҫƥ���ַ�����20142014-09-23����Ȼ2014-09-23��ƥ��
        System.out.println(s.matches("(\\d{4})\\1-\\d{2}-\\d{2}"));


        //�ַ����滻
        System.out.println(s.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})","$1*$2*$3"));
    }

    /**
     * ʹ�÷�����ȡռλ����ֵ${}
     */
    public static void reg4(){
        String s = "${param.dept_id}";
        String regex = "\\$\\{(\\S*)}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            String orginalStr = matcher.group(0);  //ƥ��${param.dept_id}
            String value = matcher.group(1); //ƥ�� param.dept_id
            System.err.println("orginalStr="+ orginalStr);
            System.err.println("value="+ value);
        }
    }

    public static void reg(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("%nEnter your regex: ");
            //1. ����������ʽ
            Pattern pattern = Pattern.compile(scanner.nextLine(),Pattern.CASE_INSENSITIVE);
            System.out.printf("Enter input string to search: ");

            // 2. ���������ַ�������ȡƥ����Matcher
            Matcher matcher = pattern.matcher(scanner.nextLine());
            boolean found = false;

            // 3. ѭ��ƥ����
            while (matcher.find()) {
                System.out.printf(
                        "I found the text \"%s\" starting at index %d and ending at index %d.%n",
                        matcher.group(), matcher.start(), matcher.end()
                );
                found = true;
            }
            if (!found) {
                System.out.printf("No match found.%n");
            }
        }
    }

} 