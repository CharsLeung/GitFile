package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        //显示区
        TextView tvResult=null;
        TextView hisText=null;

        //数字按钮
        Button btn0=null;
        Button btn1=null;
        Button btn2=null;
        Button btn3=null;
        Button btn4=null;
        Button btn5=null;
        Button btn6=null;
        Button btn7=null;
        Button btn8=null;
        Button btn9=null;

        //符号按钮
        Button btnC=null;
        Button btnAdd=null;
        Button btnSub=null;
        Button btnMul=null;
        Button btnDiv=null;
        Button btnEqu=null;
        Button btnCE=null;
        Button btnPint=null;
    Button btnDH=null;
    Button btnDB=null;



        //声明两个参数。接收tvResult前后的值
        double num1=0,num2=0;
        double Result=0;//计算结果
        int op=0;//判断操作数，
        boolean isClickEqu=false;//判断是否按了“=”按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            //显示区
            tvResult=(TextView)findViewById(R.id.textView);
            tvResult.setText(null);//初始化为null
            hisText=(TextView)findViewById(R.id.hisText);
            hisText.setText(null);//初始化为null
            //数字按钮
            btn0=(Button)findViewById(R.id.btn_num0);
            btn1=(Button)findViewById(R.id.btn_num1);
            btn2=(Button)findViewById(R.id.btn_num2);
            btn3=(Button)findViewById(R.id.btn_num3);
            btn4=(Button)findViewById(R.id.btn_num4);
            btn5=(Button)findViewById(R.id.btn_num5);
            btn6=(Button)findViewById(R.id.btn_num6);
            btn7=(Button)findViewById(R.id.btn_num7);
            btn8=(Button)findViewById(R.id.btn_num8);
            btn9=(Button)findViewById(R.id.btn_num9);
            //符号按钮
            btnC=(Button)findViewById(R.id.btn_CharC);
            btnAdd=(Button)findViewById(R.id.btn_CharAdd);
            btnSub=(Button)findViewById(R.id.btn_CharSub);
            btnMul=(Button)findViewById(R.id.btn_CharMul);
            btnDiv=(Button)findViewById(R.id.btn_CharDiv);
            btnEqu=(Button)findViewById(R.id.btn_CharEqu);
            btnCE=(Button)findViewById(R.id.btn_ce);
            btnPint=(Button)findViewById(R.id.btn_pint);
            btnDH=(Button)findViewById(R.id.btn_DH);
            btnDB=(Button)findViewById(R.id.btn_DB);

            //添加监听
            btn0.setOnClickListener(this);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn3.setOnClickListener(this);
            btn4.setOnClickListener(this);
            btn5.setOnClickListener(this);
            btn6.setOnClickListener(this);
            btn7.setOnClickListener(this);
            btn8.setOnClickListener(this);
            btn9.setOnClickListener(this);
            btnC.setOnClickListener(this);
            btnAdd.setOnClickListener(this);
            btnSub.setOnClickListener(this);
            btnMul.setOnClickListener(this);
            btnDiv.setOnClickListener(this);
            btnEqu.setOnClickListener(this);
            btnCE.setOnClickListener(this);
            btnPint.setOnClickListener(this);
            btnDH.setOnClickListener(this);
            btnDB.setOnClickListener(this);
        }
        catch (Exception e) {
            errorShow(errorStrings.abnormalError);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
                //点击C按钮Backspace一位
            case R.id.btn_CharC:
                    deleteChar();
                    break;
                //点击CE清除所有
            case R.id.btn_ce:
                    deleteAll();
                    break;
                //btn0--9---------------------------
            case R.id.btn_num0:
                    inputNum("0");
                    break;
            case R.id.btn_num1:
                    inputNum("1");
                    break;
            case R.id.btn_num2:
                    inputNum("2");
                    break;
            case R.id.btn_num3:
                    inputNum("3");
                    break;
            case R.id.btn_num4:
                    inputNum("4");
                    break;
            case R.id.btn_num5:
                    inputNum("5");
                    break;
            case R.id.btn_num6:
                    inputNum("6");
                    break;
            case R.id.btn_num7:
                    inputNum("7");
                    break;
            case R.id.btn_num8:
                    inputNum("8");
                    break;
            case R.id.btn_num9:
                    inputNum("9");
                    break;
            case R.id.btn_pint:
                inputNum(".");
                break;

                //btn+-/*=--------------------------------
            case R.id.btn_CharAdd:
                    operation(1);
                    break;

            case R.id.btn_CharSub:
                    operation(2);
                    break;
            case R.id.btn_CharMul:
                    operation(3);
                    break;
            case R.id.btn_CharDiv:
                    operation(4);
                    break;
            case R.id.btn_DH:{
                //十进制转十六进制
                String myStringNum=null;
                try {
                    myStringNum=tvResult.getText().toString();
                    num1 = Double.valueOf(myStringNum);
                    String rel=operationSelf.doubleToHex(operationSelf.doubleToBits(num1));
                    hisText.setText(rel);
                    tvResult.setText(null);
                }catch (Exception e){
                    errorShow(errorStrings.abnormalError);
                }
                break;
            }
            case R.id.btn_DB:{
                //十进制转二进制
                String myStringNum=null;
                try {
                    myStringNum=tvResult.getText().toString();
                    num1 = Double.valueOf(myStringNum);
                    String rel=operationSelf.doubleToBits(num1);
                    rel=BigDecimal.valueOf(Double.parseDouble(rel))
                            .stripTrailingZeros().toPlainString();
                    hisText.setText(rel);
                    tvResult.setText(null);
                }catch (Exception e){
                    errorShow(errorStrings.abnormalError);
                }
                break;
            }

            case R.id.btn_CharEqu:
                    operationEqu();
                    break;
                default:
                    break;
            }
        }
    public void errorShow(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
    /*
    * 点击数字键获得对应的输入
    * */
    public  void inputNum(String str){
        if(isClickEqu)
        {
            tvResult.setText(null);
            isClickEqu=false;
        }
        String myString=tvResult.getText().toString();
        myString+=str;
        tvResult.setText(myString);
    }
    /*
    点击运算符后记录运算符类型并保存第一个操作数
    * */
    public void operation(int opt){
        String myStringNum=null;
        try {
            myStringNum=tvResult.getText().toString();
        }catch (Exception e){
            errorShow(errorStrings.abnormalError);
        }

        if(myStringNum.equals(null))
        {
            errorShow(errorStrings.reminderError);
            //return;
        }
        else {
            //op等于0说明该次运算符输入是首次运算符输入
            if(op==0) {
                try {
                    num1 = Double.valueOf(myStringNum);//将运算符前的数字保存在全局变量num1中
                    tvResult.setText("");//清除文本框准备接受第二个数字
                    op = opt;
                    isClickEqu = false;
                    String opChar="  ";
                    if(op==1)
                        opChar="+";
                    if(op==2)
                        opChar="-";
                    if(op==3)
                        opChar="*";
                    if(op==4)
                        opChar="/";
                    //清除多余的0
                    String rel=BigDecimal.valueOf(Double.parseDouble(String.valueOf(num1)))
                            .stripTrailingZeros().toPlainString();
                    hisText.setText(rel+opChar);
                }catch (Exception e){
                    errorShow(errorStrings.reminderError);
                }

            }
            else{
                op = opt;//覆盖前一次的运算符输入，以最后一次为准
                tvResult.setText("");
                isClickEqu = false;
                String opChar="  ";
                if(op==1)
                    opChar="+";
                if(op==2)
                    opChar="-";
                if(op==3)
                    opChar="*";
                if(op==4)
                    opChar="/";
                String rel=BigDecimal.valueOf(Double.parseDouble(String.valueOf(num1)))
                        .stripTrailingZeros().toPlainString();
                hisText.setText(rel+opChar);
            }
        }
    }
    /**
     * 点击等号按op的操作类型值执行运算
     */
    public void operationEqu(){
        String myString2=null;
        try {
            myString2=tvResult.getText().toString();
        }catch (Exception e){
            errorShow(errorStrings.abnormalError);
        }
        if(myString2.equals(null))
        {
            errorShow("请输入操作数");
        }
        else {
            try {
                num2 = Double.valueOf(myString2);
                if(num2==0&&op==4) {
                    errorShow("请输入非零的数");
                    tvResult.setText(null);
//                    hisText.setText(null);
//                    num1=0;
                }
                else {
                    switch (op) {
                        case 0:
                            Result = num2;
                            break;
                        case 1:
                            Result = operationSelf.addSelf(num1,num2);
                            break;
                        case 2:
                            Result = operationSelf.subSelf(num1,num2);
                            break;
                        case 3:
                            Result = operationSelf.mulSelf(num1,num2);
                            break;
                        case 4:
                            Result = operationSelf.divSelf(num1,num2);
                            break;
                        default:
                            Result = 0;
                            break;
                    }
                    tvResult.setText(BigDecimal.valueOf(Double.parseDouble(String.valueOf(Result)))
                            .stripTrailingZeros().toPlainString());
                    String opChar = null;
                    if (op == 1)
                        opChar = "+";
                    if (op == 2)
                        opChar = "-";
                    if (op == 3)
                        opChar = "*";
                    if (op == 4)
                        opChar = "/";
                    String v1=BigDecimal.valueOf(Double.parseDouble(String.valueOf(num1)))
                            .stripTrailingZeros().toPlainString();
                    String v2=BigDecimal.valueOf(Double.parseDouble(String.valueOf(num2)))
                            .stripTrailingZeros().toPlainString();
                    hisText.setText(v1 + opChar + v2);
                    isClickEqu = true;
                    op = 0;//清除操作类型寄存单元的值
                }
            }catch (Exception e){
                errorShow(errorStrings.reminderError);
            }

        }
    }
    /*
    * 点击C往前删除一个字符
    * */
    public void deleteChar(){
        try {
            String myStr=tvResult.getText().toString();
            //当前文本域为空
            if(myStr.equals(null))
                tvResult.setText("");
            //当前文本域不为空可删除
            else
                tvResult.setText(myStr.substring(0, myStr.length()-1));

        } catch (Exception e) {
           // Log.getStackTraceString()
            tvResult.setText("");
        }
    }
    /*
    * 点击CE清除所有
    * */
    public void deleteAll(){
        tvResult.setText("");
        hisText.setText("");
        num1=0;
        num2=0;
        op=0;
    }
}
