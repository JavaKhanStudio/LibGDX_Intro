package sbp.gdx.prez.gvars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GVars_AnimationExplosition {

	private static TextureAtlas atlasExplosion1; 
	private static Array<TextureAtlas.AtlasRegion> explosion1Array1 ; 
	private static Animation animExplosion1 ;
	
	private static TextureAtlas atlasExplosion2; 
	private static Array<TextureAtlas.AtlasRegion> explosion1Array2 ; 
	private static Animation animExplosion2 ;
	
	private static TextureAtlas atlasExplosion3; 
	private static Array<TextureAtlas.AtlasRegion> explosion1Array3 ; 
	private static Animation animExplosion3 ;
	
	private static final float explosionSpeed = 0.01f ; 
	
	
	public static Animation<TextureRegion> getExplosion1() {
		if(atlasExplosion1 == null)
			atlasExplosion1 = new TextureAtlas(Gdx.files.internal("atlas/explosion/explosion1.atlas"));
		
		
		if(explosion1Array1 == null)
			explosion1Array1 = atlasExplosion1.findRegions("explosion1");
		
		return new Animation<TextureRegion>(explosionSpeed, explosion1Array1, Animation.PlayMode.NORMAL);
	}
	
	public static Animation<TextureRegion> getExplosion2() {
		if(atlasExplosion2 == null)
			atlasExplosion2 = new TextureAtlas(Gdx.files.internal("atlas/explosion/explosion2.atlas"));
		
		
		if(explosion1Array2 == null)
			explosion1Array2 = atlasExplosion2.findRegions("explosion2");
		
		return new Animation<TextureRegion>(explosionSpeed, explosion1Array2, Animation.PlayMode.NORMAL);
	}
	
	public static Animation<TextureRegion> getExplosion3() {
		if(atlasExplosion3 == null)
			atlasExplosion3 = new TextureAtlas(Gdx.files.internal("atlas/explosion/explosion3.atlas"));
		
		
		if(explosion1Array3 == null)
			explosion1Array3 = atlasExplosion1.findRegions("explosion3");
		
		return new Animation<TextureRegion>(explosionSpeed, explosion1Array3, Animation.PlayMode.NORMAL);
	}
	
}
