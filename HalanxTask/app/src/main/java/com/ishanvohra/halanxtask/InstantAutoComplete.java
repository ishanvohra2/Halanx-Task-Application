package com.ishanvohra.halanxtask;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

public class InstantAutoComplete extends AppCompatAutoCompleteTextView {
    public InstantAutoComplete(@NonNull Context context) {
        super(context);
    }

    public InstantAutoComplete(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InstantAutoComplete(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && getFilter()!=null)
            performFiltering(getText(), 0);
    }
}
