package org.sumon.diamondshapebottombar;

/**
 * Created by SumOn on 22,June,2020
 */
import android.annotation.SuppressLint;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.ShapePath;

/**
 * A {@link BottomAppBar} top edge that works with a Diamond shaped {@link FloatingActionButton}
 */
public class BottomAppBarCutCornersTopEdge extends BottomAppBarTopEdgeTreatment {

    private final float fabMargin;
    private final float cradleVerticalOffset;

    BottomAppBarCutCornersTopEdge(
            float fabMargin, float roundedCornerRadius, float cradleVerticalOffset) {
        super(fabMargin, roundedCornerRadius, cradleVerticalOffset);
        this.fabMargin = fabMargin;
        this.cradleVerticalOffset = cradleVerticalOffset;
    }

    @Override
    @SuppressWarnings("RestrictTo")
    public void getEdgePath(float length, float center, float interpolation, ShapePath shapePath) {
        @SuppressLint("RestrictedApi") float fabDiameter = getFabDiameter();
        if (fabDiameter == 0) {
            shapePath.lineTo(length, 0);
            return;
        }

        float diamondSize = fabDiameter / 2f;
        @SuppressLint("RestrictedApi") float middle = center + getHorizontalOffset();

        float verticalOffsetRatio = cradleVerticalOffset / diamondSize;
        if (verticalOffsetRatio >= 1.0f) {
            shapePath.lineTo(length, 0);
            return;
        }

        shapePath.lineTo(middle - (fabMargin + diamondSize - cradleVerticalOffset), 0);

        shapePath.lineTo(middle, (diamondSize - cradleVerticalOffset + fabMargin) * interpolation);

        shapePath.lineTo(middle + (fabMargin + diamondSize - cradleVerticalOffset), 0);

        shapePath.lineTo(length, 0);
    }
}
