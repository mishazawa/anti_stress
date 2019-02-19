package tofu.mishazawa.anti_stress.sketch;

import android.graphics.Point;

import java.util.ArrayList;

import processing.core.PApplet;
import tofu.mishazawa.anti_stress.sketch.objects.Door;
import tofu.mishazawa.anti_stress.sketch.util.Clickable;
import tofu.mishazawa.anti_stress.sketch.util.Drawable;
import tofu.mishazawa.anti_stress.sketch.util.ResourceManager;

import static tofu.mishazawa.anti_stress.sketch.util.Constants.DOORS_NUM;
import static tofu.mishazawa.anti_stress.sketch.util.Constants.DOOR_CLOSED_IMG;
import static tofu.mishazawa.anti_stress.sketch.util.Constants.DOOR_OPEN_IMG;

public class Main extends PApplet {
  private ArrayList<Drawable> doorsDr;
  private ArrayList<Clickable> doorsCl;
  private ResourceManager resManager;
  private Point screenSize;

  public Main (Point _screenSize) {
    screenSize = _screenSize;
    resManager = new ResourceManager(this, screenSize);
  }

  public void settings() {
    size(screenSize.x, screenSize.y);
    doorsDr = new ArrayList<>();
    doorsCl = new ArrayList<>();

    for (int i = 0; i < DOORS_NUM; i += 1) {
      Door d = new Door(this, resManager, i);
      doorsDr.add(d);
      doorsCl.add(d);
    }

    doorsCl.get(getRandomDoor()).setClosed(false);
  }

  public void setup() {
    resManager.loadRes(true, DOOR_OPEN_IMG);
    resManager.loadRes(false, DOOR_CLOSED_IMG);

  }

  public void draw() {
    background(255);
    for (Drawable d: doorsDr) {
      d.onDraw();
    }
  }

  public void touchEnded() {

    for (Clickable d: doorsCl) {
      if (d.onClick()) {
        d.setClosed(true);
        doorsCl.get(getRandomDoor()).setClosed(false);
        break;
      }
    }
  }

  private int getRandomDoor() {
    return (int) this.random(0, DOORS_NUM);
  }
}
