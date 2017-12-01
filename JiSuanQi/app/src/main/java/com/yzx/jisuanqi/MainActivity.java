package com.yzx.jisuanqi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	
    private static final int max_length = 15;
    private static final int type_div = 0;
    private static final int type_mul = 1;
    private static final int type_add = 2;
    private static final int type_sub = 3;
    private static final String[] types = {"÷", "×", "+", "-"};
	
    TextView clear, delete, div, mul, sub, add, point, equal, percent;
    TextView zero, one, two, three, four, five, six, seven, eight, nine;
    LinearLayout resultView, buttonBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initResult();
    }

    private void initResult() {
        text0 = new TextView(this);
        text1 = new TextView(this);
        text2 = new TextView(this);
        text0.setGravity(Gravity.END);
        text1.setGravity(Gravity.END);
        text2.setGravity(Gravity.END);
        text0.setTextSize(35);
        text1.setTextSize(35);
        text2.setTextSize(35);
        text0.setTextColor(getResources().getColor(R.color.text));
        text1.setTextColor(getResources().getColor(R.color.text));
        text2.setTextColor(getResources().getColor(R.color.text));
        clear();
    }

    private void clear() {
        finish = false;
        isAdd0 = false;
        isAdd1 = false;
        isAdd2 = false;
        resultView.removeAllViews();
        currentNumber = new StringBuilder("0");
        lastNumber = "";
        result = "";
        currentType = -1;
        setText0(currentNumber.toString());
        addText0();
    }

    private void setText0(String s) {
        text0.setText(s);
    }

    private boolean isAdd0 = false;

    private void addText0() {
        if (!isAdd0)
            resultView.addView(text0);
        isAdd0 = true;
    }

    private void setText1(String s) {
        text1.setText(s);
    }

    private boolean isAdd1 = false;

    private void addText1() {
        if (!isAdd1)
            resultView.addView(text1);
        isAdd1 = true;
    }

    private void setText2(String s) {
        text2.setText(s);
    }

    private boolean isAdd2 = false;

    private void addText2() {
        if (!isAdd2)
            resultView.addView(text2);
        isAdd2 = true;
    }

    private String lastNumber;
    private StringBuilder currentNumber;
    private String result;
    private int currentType;
    private TextView text0, text1, text2;
    private boolean finish = false;

    private void initView() {
        clear = findViewById(R.id.clear);
        delete = findViewById(R.id.delete);
        div = findViewById(R.id.div);
        mul = findViewById(R.id.mul);
        sub = findViewById(R.id.sub);
        add = findViewById(R.id.add);
        point = findViewById(R.id.point);
        equal = findViewById(R.id.equal);
        percent = findViewById(R.id.percent);
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        resultView = findViewById(R.id.result_view);
        buttonBase = findViewById(R.id.button_base);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getScreenHeight() * 3 / 5);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonBase.setLayoutParams(params);
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        sub.setOnClickListener(this);
        add.setOnClickListener(this);
        point.setOnClickListener(this);
        equal.setOnClickListener(this);
        percent.setOnClickListener(this);
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                clear();
                break;
            case R.id.delete:
                delete();
                break;
            case R.id.div:
                onTypeClick(type_div);
                break;
            case R.id.mul:
                onTypeClick(type_mul);
                break;
            case R.id.sub:
                onTypeClick(type_sub);
                break;
            case R.id.add:
                onTypeClick(type_add);
                break;
            case R.id.point:
                break;
            case R.id.equal:
                onEqual();
                break;
            case R.id.percent:
                break;
            case R.id.zero:
                onClick(0);
                break;
            case R.id.one:
                onClick(1);
                break;
            case R.id.two:
                onClick(2);
                break;
            case R.id.three:
                onClick(3);
                break;
            case R.id.four:
                onClick(4);
                break;
            case R.id.five:
                onClick(5);
                break;
            case R.id.six:
                onClick(6);
                break;
            case R.id.seven:
                onClick(7);
                break;
            case R.id.eight:
                onClick(8);
                break;
            case R.id.nine:
                onClick(9);
                break;
        }
    }

    private void delete() {
        if (currentType == -1) {
            if (currentNumber.length() > 1)
                currentNumber.deleteCharAt(currentNumber.length() - 1);
            else if (currentNumber.length() == 1)
                currentNumber = new StringBuilder("0");
            setText0(currentNumber.toString());
        } else {
            if (currentNumber.length() > 3) {
                currentNumber.deleteCharAt(currentNumber.length() - 1);
                if (currentNumber.length() == 3)
                    currentNumber.append(0);
                setText1(currentNumber.toString());
            }
        }
    }

    private void onTypeClick(int type) {
        if (finish) clear();
        if (currentType == -1) {
            lastNumber = builder2Long().toString();
            currentNumber = new StringBuilder();
            currentNumber.append(types[type]).append("  ");
            setText1(currentNumber.toString());
            addText1();
        } else {
            currentNumber.replace(0, 1, types[type]);
            setText1(currentNumber.toString());
        }
        currentType = type;
    }

    private void onEqual() {
        if (!finish)
            if (currentType == -1) {
                setText1("＝  " + currentNumber.toString());
                addText1();
            } else {
                if (currentNumber.toString().trim().length() == 1) {
                    result = lastNumber;
                } else {
                    long last = Long.valueOf(lastNumber);
                    long current = Long.valueOf(currentNumber.toString().trim().split(" {2}")[1]);
                    long r = 0;
                    switch (currentType) {
                        case 0:
                            if (current == 0) {
                                Toast.makeText(getApplicationContext(), "0不能做分母", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            r = last / current;
                            break;
                        case 1:
                            r = last * current;
                            break;
                        case 2:
                            r = last + current;
                            break;
                        case 3:
                            r = last - current;
                            break;
                    }
                    result = String.valueOf(r);
                }
                setText2("＝  " + result);
                addText2();
            }
        finish = true;
    }

    private void onClick(int number) {
        if (finish) clear();
        if (currentType == -1) {
            if (currentNumber.toString().equals("0")) {
                currentNumber = new StringBuilder();
            }
            if (currentNumber.toString().length() >= max_length) {
                return;
            }
        } else {
            if (currentNumber.toString().trim().length() == 4) {
                if (currentNumber.toString().trim().split(" {2}")[1].equals("0")) {
                    currentNumber = new StringBuilder(types[currentType] + "  ");
                }
            }
            if (currentNumber.toString().trim().length() > 1) {
                if (currentNumber.toString().trim().split(" {2}").length > 1) {
                    if (currentNumber.toString().trim().split(" {2}")[1].length() >= max_length) {
                        return;
                    }
                }
            }
        }
        currentNumber.append(number);
        if (isAdd0 && !isAdd1)
            setText0(currentNumber.toString());
        else if (isAdd1)
            setText1(currentNumber.toString());
    }

    private Long builder2Long() {
        return Long.valueOf(currentNumber.toString());
    }

    private int getScreenHeight() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(outMetrics);
        }
        return outMetrics.heightPixels;
    }
}
