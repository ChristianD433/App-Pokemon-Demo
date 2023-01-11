package UI_Elements;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;

import androidx.core.content.res.ResourcesCompat;

public class TypeFaceStringMaker {
    private static int font;

    public TypeFaceStringMaker(int font) {
        this.font = font;
    }

    public SpannableString build(Context context, CharSequence chars) {
        Typeface typeface = ResourcesCompat.getFont(context, font);
        if (chars == null) {
            return null;
        }
        SpannableString s = new SpannableString(chars);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            s.setSpan(new TypefaceSpan(typeface), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    public SpannableString build(Context context, int id) {
        Typeface typeface = ResourcesCompat.getFont(context, font);
        CharSequence chars = context.getString(id);
        if (chars == null) {
            return null;
        }
        SpannableString s = new SpannableString(chars);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            s.setSpan(new TypefaceSpan(typeface), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

}
