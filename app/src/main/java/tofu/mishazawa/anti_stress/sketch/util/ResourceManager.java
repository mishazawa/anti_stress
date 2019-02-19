package tofu.mishazawa.anti_stress.sketch.util;

import android.graphics.Point;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class ResourceManager {
  private PApplet p;
  private HashMap<Boolean, PImage> states;
  private Point screen;

  public ResourceManager (PApplet processing, Point screen) {
    this.p = processing;
    this.states = new HashMap<>();
    this.screen = screen;
  }

  public void loadRes (boolean f, String i) {
    System.out.println(this.p.sketchPath(i) + " -- - ");
    states.put(f, this.p.loadImage("data/"+i));
  }
  public Point getScreen () { return this.screen; }
  public PImage getRes (boolean state) {
    return states.get(state);
  }
}
