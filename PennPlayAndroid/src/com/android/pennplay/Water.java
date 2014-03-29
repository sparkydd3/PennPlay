package com.android.pennplay;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Water {

    private Paint paint;
    public static int height;
    public static int width;
    
    public static int defHeight;
    
    private Bitmap mWave;
    
    private ArrayList<Wave> waves;
    private Wave curWave;
    
    public Water(int w, int h, Bitmap wave){
        width = w;
        height = h;
        
        defHeight = height/4;
        
        paint = new Paint();
        paint.setColor(Color.rgb(35, 29, 67));
        
        mWave = wave;
        waves = new ArrayList<Wave>();
    }
    
    public void update(){
        for(int i = 0; i < waves.size(); i++){
            Wave w = waves.get(i);
            w.update();
            
            if(w.getHeight() < 0 || w.getLoc() < 0)
                waves.remove(i); 
        }
    }
    
    public void draw(Canvas canvas){
        for(int i = 0; i < waves.size(); i++){
            waves.get(i).draw(canvas);
        }
        
        Rect rect = new Rect(0, height-defHeight, width, height);
        canvas.drawRect(rect, paint);
    }
    
    public void onClick(int x){
        if(curWave != null){
            curWave.setRising(false);
        }  
        
        curWave = new Wave(x, mWave);
        waves.add(curWave);
    }
    
    public void onRelease(){
        if(curWave != null){
            curWave.setRising(false);
            curWave = null;
        }  
    }
}
