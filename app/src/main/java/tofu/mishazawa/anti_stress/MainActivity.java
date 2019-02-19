package tofu.mishazawa.anti_stress;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import processing.android.CompatUtils;
import processing.android.PFragment;
import processing.core.PApplet;
import tofu.mishazawa.anti_stress.sketch.Main;

public class MainActivity extends AppCompatActivity {
  private PApplet sketch;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FrameLayout frame = new FrameLayout(this);
    frame.setId(CompatUtils.getUniqueViewId());
    setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    sketch = new Main(getScreenSize(this));
    PFragment fragment = new PFragment(sketch);
    fragment.setView(frame, this);
  }

  @Override
  public void onRequestPermissionsResult(int reqCode, String[] permissions, int[] res) {
    if (sketch != null) sketch.onRequestPermissionsResult(reqCode, permissions, res);
  }

  @Override
  public void onNewIntent(Intent intent) {
    if (sketch != null) sketch.onNewIntent(intent);
  }

  private Point getScreenSize (Context c) {
    Point point = new Point();
    try {
      ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
    } catch (NullPointerException e) {
      Log.d("Mixa", "Null pointer exception no display");
    }
    return point;
  }
}
