package StudentManagementSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {
        verificationCode();
        ArrayList<User> user_list=new ArrayList<>();
        outloop1:
        while (true){
            System.out.println("----------欢迎来到学生管理系统----------");
            System.out.println("请选择操作1登录 2注册 3忘记密码");
            Scanner sc=new Scanner(System.in);
            String choose=sc.next();
            switch (choose){
                case "1"-> {
                    if(login(user_list)){
                        System.out.println("登录成功");
                        break outloop1;
                    }
                }
                case "2"-> register(user_list);
                case "3"-> forgetPassword(user_list);
                default -> System.out.println("没有这个选项!");
            }
        }

        ArrayList<Student> stu_list=new ArrayList<>();
        outloop2:
        while (true) {
            System.out.println("----------欢迎来到学生管理系统----------");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出");
            System.out.println("请输入您的选择:");

            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1" -> addStudent(stu_list);
                case "2" -> deleteStudent(stu_list);
                case "3" -> updateStudent(stu_list);
                case "4" -> queryStudent(stu_list);
                case "5" -> {
                    System.out.println("退出");
                    break outloop2;
                }
                default -> System.out.println("没有这个选项!");
            }
        }
    }

    //注册
    public static void register(ArrayList<User> list){
        Scanner sc=new Scanner(System.in);

        System.out.println("请输入用户名:");
        String username=sc.next();

        //用户名唯一
        for(int i=0;i< list.size();i++){
            if(list.get(i).getUsername().equals(username)){
                System.out.println("该用户已存在!");
                return;
            }
        }

        //用户名长度必须在3~15位之间
        if(username.length()<3 || username.length()>15){
            System.out.println("用户名长度必须在3~15位之间");
            return;
        }

        //只能是字母加数字的组合，但是不能是纯数字
        for(int i=0;i<username.length();i++){
            if((username.charAt(i)>='a' && username.charAt(i)<='z')||
                    (username.charAt(i)>='A' && username.charAt(i)<='Z')||
                    (username.charAt(i)>='0' && username.charAt(i)<='9'))
                continue;
            else {
                System.out.println("用户名只能是字母加数字的组合，但是不能是纯数字");
                return;
            }
        }
        boolean purenumber=true;
        for(int i=0;i<username.length();i++){
            if(username.charAt(i)<'0' || username.charAt(i)>'9')
                purenumber=false;
        }
        if(purenumber){
            System.out.println("用户名只能是字母加数字的组合，但是不能是纯数字");
            return;
        }

        //密码
        System.out.println("请输入密码:");
        String password1=sc.next();
        System.out.println("请再次输入密码:");
        String password2=sc.next();
        if(password1.equals(password2))
            ;
        else {
            System.out.println("两次输入的密码不一致!");
            return;
        }

        //身份证号码
        System.out.println("请输入身份证号码:");
        String idcardnum=sc.next();

        //长度为18位
        if (idcardnum.length()!=18){
            System.out.println("身份证号码长度必须为18位!");
            return;
        }

        //不能以0为开头
        if(idcardnum.charAt(0)=='0'){
            System.out.println("身份证号码不能以0为开头!");
            return;
        }

        //前17位，必须都是数字
        for(int i=0;i<idcardnum.length()-1;i++){
            if(idcardnum.charAt(i)>='0' && idcardnum.charAt(i)<='9')
                continue;
            else {
                System.out.println("身份证号码前17位，必须都是数字!");
                return;
            }
        }

        if((idcardnum.charAt(17)>='0' && idcardnum.charAt(17)<='9')||
                idcardnum.charAt(17)=='x' || idcardnum.charAt(17)=='X')
            ;
        else {
            System.out.println("身份证号码最后一位只能是是数字或大小写x!");
            return;
        }

        //手机号
        System.out.println("请输入手机号码:");
        String telnum=sc.next();

        //长度为11位
        if(telnum.length()!=11){
            System.out.println("手机号码长度必须是11位!");
            return;
        }

        //不能以0为开头
        if (telnum.charAt(0)=='0'){
            System.out.println("手机号码不能以0为开头!");
            return;
        }

        //必须都是数字
        for(int i=0;i<telnum.length();i++){
            if(telnum.charAt(i)<'0' || telnum.charAt(i)>'9'){
                System.out.println("手机号码必须都是数字!");
                return;
            }
        }
        User u=new User(username,password1,idcardnum,telnum);
        list.add(u);
        System.out.println("注册成功!");
    }

    //登录
    public static boolean login(ArrayList<User> list){
        Scanner sc=new Scanner(System.in);
        for(int j=1;j<4;j++){
            System.out.println("请输入用户名:");
            String username=sc.next();
            System.out.println("请输入密码:");
            String password=sc.next();
            String verificationcode=verificationCode();
            System.out.println("验证码:"+verificationcode);
            System.out.println("请输入验证码:");
            String vcode=sc.next();

            //用户未注册
            if(searchUser(list, username)<0){
                System.out.println("用户名未注册，请先注册!");
                return false;
            }

            //验证码错误
            if(!vcode.equalsIgnoreCase(verificationcode)){
                System.out.println("验证码错误,请重新输入!");
                continue;
            }

            //密码错误
            if(!list.get(searchUser(list,username)).getPassword().equals(password)){
                System.out.println("密码错误,请重新输入!");
                continue;
            }

            //用户名,密码,验证码都正确
//            System.out.println("登录成功!");
            return true;
        }
        return false;
    }

    //忘记密码
    public static void forgetPassword(ArrayList<User> list){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username=sc.next();

        int u_index=searchUser(list,username);
        if (u_index<0){
            System.out.println("未注册!");
            return;
        }

        System.out.println("请输入身份证号码:");
        String idcardnum=sc.next();
        System.out.println("请输入手机号码:");
        String telnum=sc.next();

        if(!list.get(u_index).getIdcardnum().equals(idcardnum)||
                !list.get(u_index).getTelnum().equals(telnum)){
            System.out.println("账号信息不匹配，修改失败!");
            return;
        }
        else {
            System.out.println("请输入修改后的密码:");
            String password=sc.next();
            list.get(u_index).setPassword(password);
            System.out.println("密码修改成功!");
        }
    }

    //生成验证码
    public static String verificationCode(){
        Random r=new Random();
        char[] code=new char[5];
        for(int i=0;i< code.length-1;i++){
            code[i]=(char)('A'+r.nextInt(26));
            if (r.nextInt(2)==1)
                code[i]=(char) (code[i]+32);
        }
        code[code.length-1]=(char)(r.nextInt(10)+'0');
        //形如ScVn2,打乱
        for(int i=0;i<code.length;i++){
            int j=r.nextInt(5);
            char c=code[i];
            code[i]=code[j];
            code[j]=c;
        }
        String s=new String(code);
//        System.out.println("验证码:"+s);
        return s;
    }

    //查询username是否在集合user_list中,返回索引
    public static int searchUser(ArrayList<User> list,String username){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUsername().equals(username))
                return i;
        }
        return -1;
    }

    //添加学生
    public static void addStudent(ArrayList<Student> list){
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入要添加学生的id:");
        String id=sc.next();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                System.out.println("该学生已存在!");
                return;
            }
        }
        System.out.println("请输入要添加学生的姓名:");
        String name=sc.next();
        System.out.println("请输入要添加学生的年龄:");
        int age=sc.nextInt();
        System.out.println("请输入要添加学生的家庭住址:");
        String address=sc.next();
        Student s=new Student();
        s.setId(id);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);
        list.add(s);
        System.out.println("添加成功!");
    }

    //删除学生
    public static void deleteStudent(ArrayList<Student> list){
        if (list.size()==0){
            System.out.println("当前无学生信息,请添加后再删除!");
            return;
        }
        System.out.println("请输入要删除学生的id:");
        Scanner sc=new Scanner(System.in);
        String id=sc.next();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                list.remove(i);
                System.out.println("删除成功!");
                return;
            }
        }
        System.out.println("id不存在!");
    }

    //修改学生
    public static void updateStudent(ArrayList<Student> list){
        if(list.size()==0){
            System.out.println("当前无学生信息，请添加后再修改!");
            return;
        }
        System.out.println("请输入要修改学生的id:");
        Scanner sc=new Scanner(System.in);
        String id=sc.next();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId().equals(id)){
                System.out.println("请输入修改后的姓名:");
                String name=sc.next();
                System.out.println("请输入修改后的年龄:");
                int age=sc.nextInt();
                System.out.println("请输入修改后的家庭住址:");
                String address=sc.next();
                list.get(i).setName(name);
                list.get(i).setAge(age);
                list.get(i).setAddress(address);
                System.out.println("修改成功!");
                return;
            }
        }
        System.out.println("id不存在!");
    }

    //查询学生
    public static void queryStudent(ArrayList<Student> list){
        if(list.size()==0){
            System.out.println("当前无学生信息，请添加后再查询!");
            return;
        }
        System.out.println("id\t姓名\t年龄\t家庭住址");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i).getId()+"\t");
            System.out.print(list.get(i).getName()+"\t");
            System.out.print(list.get(i).getAge()+"\t");
            System.out.print(list.get(i).getAddress()+"\n");
        }
    }
}


//  610431200205153411
//  13629182134