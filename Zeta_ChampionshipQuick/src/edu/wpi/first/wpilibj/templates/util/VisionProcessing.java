
package edu.wpi.first.wpilibj.templates.util;

/**
 * Sample program to use NIVision to find rectangles in the scene that are illuminated
 * by a LED ring light (similar to the model from FIRSTChoice). The camera sensitivity
 * is set very low so as to only show light sources and remove any distracting parts
 * of the image.
 * 
 * The CriteriaCollection is the set of criteria that is used to filter the set of
 * rectangles that are detected. In this example we're looking for rectangles with
 * a minimum width of 30 pixels and maximum of 400 pixels.
 * 
 * The algorithm first does a color threshold operation that only takes objects in the
 * scene that have a bright green color component. Then a small object filter
 * removes small particles that might be caused by green reflection scattered from other 
 * parts of the scene. Finally all particles are scored on rectangularity, and aspect ratio,
 * to determine if they are a target.
 *
 * Look in the VisionImages directory inside the project that is created for the sample
 * images.
 */

public class VisionProcessing {

    //Camera constants used for distance calculation
    public static final int Y_IMAGE_RES = 480;		//X Image resolution in pixels, should be 120, 240 or 480
    public static final double VIEW_ANGLE = 49;		//Axis M1013
    //final double VIEW_ANGLE = 41.7;		//Axis 206 camera
    //final double VIEW_ANGLE = 37.4;  //Axis M1011 camera
    public static final double PI = 3.141592653;

    //Score limits used for target identification
    public static final int  RECTANGULARITY_LIMIT = 40;
    public static final int ASPECT_RATIO_LIMIT = 55;

    //Score limits used for hot target determination
    public static final int TAPE_WIDTH_LIMIT = 50;
    public static final int  VERTICAL_SCORE_LIMIT = 50;
    public static final int LR_SCORE_LIMIT = 50;

    //Minimum area of particles to be considered
    public static final int AREA_MINIMUM = 150;

    //Maximum number of particles to process
    public static final int MAX_PARTICLES = 8;
    
    public class Scores {
        
        public double rectangularity;
        public double aspectRatioVertical;
        public double aspectRatioHorizontal;
    }
    
    public class TargetReport {
        
	public int verticalIndex;
	public int horizontalIndex;
	public boolean Hot;
	public double totalScore;
	public double leftScore;
	public double rightScore;
	public double tapeWidthScore;
	public double verticalScore;
    } 
}
        