package com.classichu.classichu.basic.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.classichu.classichu.R;
import com.classichu.classichu.basic.tool.SizeTool;

/**
 * Created by louisgeek on 2017/6/9.
 */

public class ClassicInputLayout extends LinearLayout {

    private EditText mInputEditText;
    private ImageButton mPasswordToggleImageButton;
    private ImageView mContentClearImageView;
    private ImageView mErrorImageView;
    private Context mContext;
    private boolean mPasswordToggleImageButtonEnabled = false;
    private boolean mIsPassword = false;
    private boolean mContentClearImageViewEnabled = true;
    private final static int DEFALUT_PADDING_LEFT_RIGHT_START_END = 12;

    public ClassicInputLayout(Context context) {
        this(context, null);
    }

    public ClassicInputLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initInputEditText();
        initErrorBtn();
        initClearBtn();
        initPasswordBtn();
        //
        this.setGravity(Gravity.CENTER_VERTICAL);
        //this.setBackgroundColor(Color.parseColor("#B3BCBCBC"));
        //
        initTypedArray(attrs);
    }

    private void initTypedArray(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.ClassicInputLayout);
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int index = ta.getIndex(i);
            if (index == R.styleable.ClassicInputLayout_classic_is_password) {
                setIsPassword(ta.getBoolean(index, false));
            } else if (index == R.styleable.ClassicInputLayout_classic_password_btn_enable) {
                setPasswordToggleImageButtonEnabled(ta.getBoolean(index, false));
            } else if (index == R.styleable.ClassicInputLayout_classic_password_btn_drawable) {
                setPasswordToggleImageButtonDrawable(ta.getDrawable(index));
            } else if (index == R.styleable.ClassicInputLayout_classic_clear_btn_drawable) {
                setContentClearImageViewDrawable(ta.getDrawable(index));
            } else if (index == R.styleable.ClassicInputLayout_classic_clear_btn_enable) {
                setContentClearImageButtonEnabled(ta.getBoolean(index, false));
            } else if (index == R.styleable.ClassicInputLayout_classic_edit_hint) {
                setHint(ta.getString(index));
            } else if (index == R.styleable.ClassicInputLayout_classic_edit_hint_color) {
                setHintColor(ta.getColor(index, Color.parseColor("#42000000")));
            } else if (index == R.styleable.ClassicInputLayout_classic_edit_text) {
                setText(ta.getString(index));
            } else if (index == R.styleable.ClassicInputLayout_classic_edit_text_color) {
                setTextColor(ta.getColor(index, Color.parseColor("#8A000000")));
            } else if (index == R.styleable.ClassicInputLayout_classic_edit_text_size) {
                int defValue = getResources().getDimensionPixelSize(R.dimen.classic_text_size_primary);
                setTextSize(ta.getDimensionPixelSize(index, defValue));
            }
        }
        ta.recycle();
    }


    private void initPasswordBtn() {
        mPasswordToggleImageButton = new ImageButton(mContext);
        mPasswordToggleImageButton.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mPasswordToggleImageButton.setBackgroundResource(R.drawable.selector_classic_btn_item_click_bg);
        mPasswordToggleImageButton.setImageResource(R.drawable.selector_classic_password_bg);
        //src 源  就是 80000000    dst 目标 就是 selector_classic_password_bg
        mPasswordToggleImageButton.setColorFilter(Color.parseColor("#80000000"),
                PorterDuff.Mode.SRC_IN);
        int padding = SizeTool.dp2px(DEFALUT_PADDING_LEFT_RIGHT_START_END);
        mPasswordToggleImageButton.setPadding(padding, mPasswordToggleImageButton.getPaddingTop()
                , padding, mPasswordToggleImageButton.getPaddingBottom());
        ViewCompat.setPaddingRelative(mPasswordToggleImageButton, padding, mPasswordToggleImageButton.getPaddingTop(),
                padding, mPasswordToggleImageButton.getPaddingBottom());
        mPasswordToggleImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInputEditText == null) {
                    return;
                }
                togglePassword();
            }
        });
        configPasswordVisible();

        this.addView(mPasswordToggleImageButton);
    }

    private boolean lastShow = true;

    private void initErrorBtn() {

        mErrorImageView = new ImageView(mContext);
        ViewGroup.LayoutParams vg_lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mErrorImageView.setLayoutParams(vg_lp);
        mErrorImageView.setImageResource(R.drawable.ic_error_black_24dp);
        //src 源  就是 D9AAACAD    dst 目标 就是 ic_cancel_black_24dp
        mErrorImageView.setColorFilter(Color.parseColor("#FF0030"),
                PorterDuff.Mode.SRC_IN);
        //  mContentClearImageButton.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent));
        ViewCompat.setBackground(mErrorImageView, null);
        mErrorImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //
                CharSequence error = (CharSequence) mErrorImageView.getTag(R.id.id_view_error_msg_holder);
                PopupWindow popup = (PopupWindow) mErrorImageView.getTag(R.id.id_view_error_popup_holder);
                if (popup != null && lastShow) {
                    popup.dismiss();
                } else {
                    setError(error);
                }
                //
                lastShow = !lastShow;
            }
        });
        mErrorImageView.setVisibility(GONE);
        this.addView(mErrorImageView);

        //!!!
        //initErrorBtnPadding();
    }

    private void initClearBtn() {
        mContentClearImageView = new ImageView(mContext);
        ViewGroup.LayoutParams vg_lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mContentClearImageView.setLayoutParams(vg_lp);
        mContentClearImageView.setImageResource(R.drawable.ic_cancel_black_24dp);
        //src 源  就是 D9AAACAD    dst 目标 就是 ic_cancel_black_24dp
        mContentClearImageView.setColorFilter(Color.parseColor("#D9AAACAD"),
                PorterDuff.Mode.SRC_IN);
        //  mContentClearImageButton.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent));
        ViewCompat.setBackground(mContentClearImageView, null);
        mContentClearImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInputEditText == null) {
                    return;
                }
                mInputEditText.requestFocus();
                mInputEditText.setText(null);
            }
        });
        switchVisibilityClearBtn();
        this.addView(mContentClearImageView);
    }

    private String lastText;

    private void initInputEditText() {
        mInputEditText = new EditText(mContext);
        LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ll_lp.weight = 1.0f;
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        mInputEditText.setLayoutParams(ll_lp);
        mInputEditText.setLines(1);
        //设置左padding
        mInputEditText.setPadding(mInputEditText.getPaddingLeft() + SizeTool.dp2px(5),
                mInputEditText.getPaddingTop(),
                mInputEditText.getPaddingRight(),
                mInputEditText.getPaddingBottom());
        mInputEditText.setHintTextColor(Color.parseColor("#42000000"));
        ViewCompat.setBackground(mInputEditText, null);
        mInputEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 会影响内部view 的 setSelected 状态
                ClassicInputLayout.this.setSelected(hasFocus);
                // ClassicInputLayout.this.setActivated(hasFocus);
            }
        });
        mInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(lastText)) {
                    return;
                }
                switchVisibilityClearBtn();
                //变动后隐藏
                mErrorImageView.setVisibility(GONE);
                //
                lastText = s.toString();
            }
        });
        this.addView(mInputEditText);
    }

    private void switchVisibilityClearBtn() {
        if (mContentClearImageView == null || mInputEditText == null) {
            return;
        }

        if (mInputEditText.getText().length() > 0 && mContentClearImageViewEnabled) {
            //mContentClearImageView.setVisibility(VISIBLE);
            mContentClearImageView.animate().cancel();
            ViewCompat.animate(mContentClearImageView).cancel();
            ViewCompat.animate(mContentClearImageView)
                    .alpha(1f)
                    .setDuration(200L)
                    .setInterpolator(new LinearOutSlowInInterpolator())
                    .setListener(new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(View view) {
                            view.setVisibility(VISIBLE);
                        }
                    }).start();
        } else {
            // mContentClearImageView.setVisibility(GONE);
            ViewCompat.animate(mContentClearImageView).cancel();
            ViewCompat.animate(mContentClearImageView)
                    .alpha(0f)
                    .setDuration(200L)
                    .setInterpolator(new FastOutLinearInInterpolator())
                    .setListener(new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(View view) {
                            view.setVisibility(GONE);
                        }
                    }).start();
        }
        //!!!
        initErrorBtnPadding();
    }

    /**
     * 如果有密码查看按钮  或者  内容清除按钮
     * <p>
     * 调整错误按钮的 padding
     */
    private void initErrorBtnPadding() {
        int padding = SizeTool.dp2px(DEFALUT_PADDING_LEFT_RIGHT_START_END);
        int paddding_new = padding;
        if (mIsPassword && mPasswordToggleImageButtonEnabled) {
            paddding_new = SizeTool.dp2px(2);
        }
        if (mContentClearImageView != null && mContentClearImageView.getVisibility() == VISIBLE) {
            paddding_new = SizeTool.dp2px(2);
        }
        mErrorImageView.setPadding(paddding_new,
                mErrorImageView.getPaddingTop()
                , paddding_new, mErrorImageView.getPaddingBottom());
        ViewCompat.setPaddingRelative(mErrorImageView,
                paddding_new,
                mErrorImageView.getPaddingTop(),
                paddding_new, mErrorImageView.getPaddingBottom());

    }

    /**
     * 如果有密码查看按钮  调整清除按钮的 padding
     */
    private void initClearBtnPadding() {
        int padding = SizeTool.dp2px(DEFALUT_PADDING_LEFT_RIGHT_START_END);
        int paddding_new = padding;
        if (mIsPassword && mPasswordToggleImageButtonEnabled) {
            paddding_new = SizeTool.dp2px(2);
        }
        mContentClearImageView.setPadding(paddding_new,
                mContentClearImageView.getPaddingTop()
                , paddding_new,
                mContentClearImageView.getPaddingBottom());
        ViewCompat.setPaddingRelative(mContentClearImageView,
                paddding_new,
                mContentClearImageView.getPaddingTop(),
                paddding_new,
                mContentClearImageView.getPaddingBottom()
        );
    }


    @Deprecated //comm
    private void switchPasswordVisible2() {
        int pos = mInputEditText.getSelectionEnd();
        if (mInputEditText.getInputType() != (InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            //隐藏
            mInputEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            //显示
            mInputEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        mInputEditText.setSelection(pos);
    }

    @Deprecated //comm
    private void switchPasswordVisible() {
        int pos = mInputEditText.getSelectionEnd();
        if (mInputEditText.getTransformationMethod() instanceof PasswordTransformationMethod) {
            mInputEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            mInputEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        mInputEditText.setSelection(pos);
    }

    private void togglePassword() {
        int selection = mInputEditText.getSelectionEnd();
        if (mInputEditText.getTransformationMethod() instanceof PasswordTransformationMethod) {
            mInputEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mPasswordToggleImageButton.setActivated(true);//明文icon   眼睛斜杠
        } else {
            mInputEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            mPasswordToggleImageButton.setActivated(false);//密文icon  眼睛
        }
        mInputEditText.setSelection(selection);
    }

    private void configPasswordVisible() {
        if (mIsPassword) {
            //是密码
            mInputEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            //
            if (mPasswordToggleImageButtonEnabled) {
                //密码切换按钮可见
                mPasswordToggleImageButton.setVisibility(VISIBLE);
            } else {
                //密码切换不可见
                mPasswordToggleImageButton.setVisibility(GONE);
            }
        } else {
            //不是密码
            mInputEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            //
            //密码切换不可见
            mPasswordToggleImageButton.setVisibility(GONE);
        }

        //!!!!!!!!
        initClearBtnPadding();
        initErrorBtnPadding();
    }

    public ClassicInputLayout setText(CharSequence text) {
        mInputEditText.setText(text);
        return this;
    }


    public ClassicInputLayout setTextColor(int color) {
        mInputEditText.setTextColor(color);
        return this;
    }

    public ClassicInputLayout setTextSize(int size) {
        mInputEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        return this;
    }

    public ClassicInputLayout setHint(CharSequence hint) {
        mInputEditText.setHint(hint);
        return this;
    }

    public ClassicInputLayout setHintColor(int color) {
        mInputEditText.setHintTextColor(color);
        return this;
    }

    public ClassicInputLayout setContentClearImageViewDrawable(Drawable drawable) {
        mContentClearImageView.setImageDrawable(drawable);
        return this;
    }

    public ClassicInputLayout setPasswordToggleImageButtonDrawable(Drawable drawable) {
        mPasswordToggleImageButton.setImageDrawable(drawable);
        return this;
    }

    public ClassicInputLayout setPasswordToggleImageButtonEnabled(boolean enabled) {
        this.mPasswordToggleImageButtonEnabled = enabled;
        configPasswordVisible();
        return this;
    }

    public ClassicInputLayout setIsPassword(boolean isPassword) {
        this.mIsPassword = isPassword;
        configPasswordVisible();
        return this;
    }

    public ClassicInputLayout setContentClearImageButtonEnabled(boolean enabled) {
        this.mContentClearImageViewEnabled = enabled;
        switchVisibilityClearBtn();
        return this;
    }


    public EditText getInput() {
        return mInputEditText;
    }

    public String getText() {
        return mInputEditText.getText().toString();
    }

    public void setError(final CharSequence text) {
        //!!!
        initErrorBtnPadding();

        mErrorImageView.setVisibility(VISIBLE);
        mErrorImageView.setTag(R.id.id_view_error_msg_holder, text);
        mErrorImageView.post(new Runnable() {
            @Override
            public void run() {

                //View.post 用来解决 GONE --> VISIBLE 后 第一次显示位置错误
                final TextView textView = new TextView(mContext);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setText(text);
                int padding = 10;
                int rightMargin = SizeTool.dp2px(10);//10dp
                textView.setPadding(padding, padding, padding, padding);
                textView.setBackgroundColor(Color.WHITE);
                // textView.setTextColor(Color.WHITE);
                PopupWindow popupWindow = new PopupWindow(mContext);
                popupWindow.setContentView(textView);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                int[] location = new int[2];
                mErrorImageView.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                int xOffset = rightMargin;
                int yOffset = 5;
                popupWindow.showAtLocation(mErrorImageView, Gravity.RIGHT | Gravity.END | Gravity.TOP,
                        xOffset, y + mErrorImageView.getHeight() + yOffset);
           /*     PopupWindow popupWindow = new PopupWindow(mContext);
                popupWindow.setContentView(textView);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                int contentViewWidth = ViewTool.getMeasuredWidthMy(popupWindow.getContentView());//文字如果换行就不准了
                if (contentViewWidth > ScreenTool.getScreenWidth()*0.85) {//大概85% 直接填充父布局
                    //设置填充父布局 此时的左右相对偏移即便设置了也不起作用
                    popupWindow.setWidth(LayoutParams.MATCH_PARENT);
                    popupWindow.showAsDropDown(mErrorImageView, 0, 5);//显示
                } else {
                    popupWindow.setWidth(LayoutParams.WRAP_CONTENT);
                    //###popupWindow.showAsDropDown(mErrorImageView, 10, 5);
                    //  Gravity.BOTTOM|Gravity.END 以mErrorImageView右下角为原点  向x负方向偏移 contentViewWidth
                    PopupWindowCompat.showAsDropDown(popupWindow, mErrorImageView, -contentViewWidth, 5, Gravity.BOTTOM | Gravity.END | Gravity.RIGHT);
                }*/
                //设置tag
                mErrorImageView.setTag(R.id.id_view_error_popup_holder, popupWindow);
                /*//针对单行文本居中
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (textView.getLineCount() == 1) {
                            textView.setGravity(Gravity.CENTER);
                        } else {
                            textView.setGravity(Gravity.TOP | Gravity.START);
                        }
                    }
                });*/
            }
        });


    }

}
