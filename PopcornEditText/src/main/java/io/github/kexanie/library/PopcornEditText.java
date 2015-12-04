package io.github.kexanie.library;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jawnnypoo.physicslayout.PhysicsRelativeLayout;

public class PopcornEditText extends EditText {
    private final int DEFAULT_TTL = 7000;

    private int mTTL;
    private int mPopcornColor;
    private float mPopcornSize;
    private float mEndY;

    private ViewGroup content;
    private PhysicsRelativeLayout physicsLayout;

    float startX;
    float startY;

    public PopcornEditText(Context context) {
        super(context);
    }

    public PopcornEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PopcornEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (0 == before) {
                    fire(s.charAt(start));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PopcornEditText);
        try {
            mTTL = typedArray.getInteger(R.styleable.PopcornEditText_TTL, DEFAULT_TTL);
            mPopcornColor = typedArray.getColor(
                    R.styleable.PopcornEditText_popcorn_color, getCurrentTextColor());
            mPopcornSize = typedArray.getDimension(R.styleable.PopcornEditText_popcorn_size, getTextSize());
            mEndY = typedArray.getDimension(R.styleable.PopcornEditText_endY, getHeight());
        } finally {
            typedArray.recycle();
        }

        content= (ViewGroup) ((Activity) getContext()).findViewById(android.R.id.content);
        ViewGroup rootView = (ViewGroup) ((Activity) getContext())
                .getLayoutInflater()
                .inflate(R.layout.layout_physics, content);

        physicsLayout = (PhysicsRelativeLayout) rootView.findViewById(R.id.physics_layout);
    }

    private void fire(Character c) {
        final TextView textView = new TextView(getContext());
        textView.setText(c.toString());
        textView.setTextColor(mPopcornColor);
        textView.setTextSize(mPopcornSize);

        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        content.addView(textView, params);

        startX = getLayout().getPrimaryHorizontal(getSelectionStart());
        startY = getRootView().getHeight();

        textView.setX(startX);
        textView.setY(startY);
        textView.animate().translationY(mEndY)
                .setInterpolator(new DecelerateInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        content.removeView(textView);
                        physicsLayout.addView(textView);
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                physicsLayout.removeView(textView);
                            }
                        }, mTTL);
                    }
                })
                .start();
    }

    int getPopcornColor() {
        return mPopcornColor;
    }

    void setPopcornColor(int color) {
        mPopcornColor = color;
    }

    int getTTL() {
        return mTTL;
    }

    void setTTL(int ttl) {
        mTTL = ttl;
    }

    float getPopcornSize() {
        return mPopcornSize;
    }

    void setPopcornSize(float size) {
        if (size > 0) {
            mPopcornSize = size;
        }
    }

    float getEndY() {
        return mEndY;
    }

    void setEndY(float endY) {
        mEndY = endY;
    }
}
