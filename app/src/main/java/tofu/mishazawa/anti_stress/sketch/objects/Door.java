package tofu.mishazawa.anti_stress.sketch.objects;


import android.graphics.Point;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tofu.mishazawa.anti_stress.sketch.util.Clickable;
import tofu.mishazawa.anti_stress.sketch.util.Drawable;
import tofu.mishazawa.anti_stress.sketch.util.ResourceManager;

import static tofu.mishazawa.anti_stress.sketch.util.Constants.DOORS_COLS;
import static tofu.mishazawa.anti_stress.sketch.util.Constants.DOORS_ROWS;
import static tofu.mishazawa.anti_stress.sketch.util.Constants.DOOR_HEIGHT;
import static tofu.mishazawa.anti_stress.sketch.util.Constants.DOOR_WIDTH;

public class Door implements Drawable, Clickable {
  private PApplet p;
  private PVector coords;
  private boolean open = false;
  private ResourceManager res;

  public Door (PApplet processing, ResourceManager resManager, int index) {
    this.p = processing;
    this.res = resManager;
    this.coords = new PVector();
    this.setLocation(index);
  }

  public void onDraw () {
    this.p.pushMatrix();

    this.p.translate(this.coords.x, this.coords.y);

    final PImage tile = res.getRes(this.open);
    this.p.image(tile, 0, 0, tile.width * 2, tile.height * 2);

    this.p.popMatrix();
  }

  public boolean onClick () {
    boolean horColl = this.coords.x < this.p.mouseX && this.p.mouseX < this.coords.x + DOOR_WIDTH;
    if (!horColl) return false;
    boolean vertColl = this.coords.y < this.p.mouseY && this.p.mouseY < this.coords.y + DOOR_HEIGHT;
    if (!vertColl) return false;
    return this.open;
  }

  public void setClosed (boolean state) {
    this.open = !state; // xz )))))
  }

  private void setLocation (int index) {
    int row = index / DOORS_COLS;
    int col = index % DOORS_COLS;
    int offsetHor = res.getScreen().x / DOORS_COLS;
    int offsetVert = res.getScreen().y / DOORS_ROWS;
    this.coords.set(col * offsetHor + (DOOR_WIDTH / 2), row * offsetVert + (DOOR_WIDTH / 4));
  }
}
