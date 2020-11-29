package regexp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarnessV5 {

    public static void main(String[] args) {
        reg4();
    }

    /**
     *  通过正则分组给定日期，提取年、月、日
     *  group(0)表示匹配的整个字符串
     *  group(1)表示第1个子串
     *  group(2)表示第2个子串
     */
    public static void reg2(){
        String s = "2014-09-23";
        // 创建patten对象
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        // 获取匹配器
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            System.err.println("待匹配字符串="+matcher.group(0));
            System.out.println(matcher.group(1)+"&&"+matcher.group(2)+"&&"+matcher.group(3));
        }
    }

    /**
     *  通过正则分组 + 反向引用（\\ 或者使用$ 进行反向引用）
     *  group(0)表示匹配的整个字符串
     *  group(1)表示第1个子串
     *  group(2)表示第2个子串
     */
    public static void reg3(){
        String s = "2014-09-23";

        // 反向引用(\\d{4})\\1-\\d{2}-\\d{2}，其中\\1是转义了，是引用匹配到的第一个分组即2014
        // 即需要匹配字符串：20142014-09-23，显然2014-09-23不匹配
        System.out.println(s.matches("(\\d{4})\\1-\\d{2}-\\d{2}"));


        //字符串替换
        System.out.println(s.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})","$1*$2*$3"));
    }

    /**
     * 使用分组提取占位符的值${}
     */
    public static void reg4(){
        String s = "${param.dept_id}";
        String regex = "\\$\\{(\\S*)}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            String orginalStr = matcher.group(0);  //匹配${param.dept_id}
            String value = matcher.group(1); //匹配 param.dept_id
            System.err.println("orginalStr="+ orginalStr);
            System.err.println("value="+ value);
        }
    }

    public static void reg(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("%nEnter your regex: ");
            //1. 编译正则表达式
            Pattern pattern = Pattern.compile(scanner.nextLine(),Pattern.CASE_INSENSITIVE);
            System.out.printf("Enter input string to search: ");

            // 2. 根据输入字符串，获取匹配器Matcher
            Matcher matcher = pattern.matcher(scanner.nextLine());
            boolean found = false;

            // 3. 循环匹配结果
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