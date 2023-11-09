package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import sbp.gdx.prez.gvars.FVars_Texture;

public class Exemple_Dd_CamerasTypes extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    OrthographicCamera orthoCamera;
    PerspectiveCamera perspCamera;
    boolean useOrtho = true;

    float distanceRapide = 0; 
    float distanceLent = 0;
    final int sizeImg = 200;
    boolean isPaused = false; 
    
    ModelBatch modelBatch;
    Model model;
    ModelInstance modelInstance;

    @Override
    public void create () {
        batch = new SpriteBatch();
        modelBatch = new ModelBatch();
        img = new Texture(FVars_Texture.BDXIO_LOGO);

        // Initialize cameras
        orthoCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthoCamera.setToOrtho(false);
        
        perspCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspCamera.position.set(0, 0, 2f);
        perspCamera.lookAt(0,0,0);
        perspCamera.near = 1;
        perspCamera.far = 100;
        perspCamera.update();
        
        Material material = new Material(TextureAttribute.createDiffuse(img));
        MeshPartBuilder builder;
        
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        builder = modelBuilder.part("quad", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates, material);
        builder.rect(
                -0.5f, -0.5f, 0,
                 0.5f, -0.5f, 0,
                 0.5f,  0.5f, 0,
                -0.5f,  0.5f, 0,
                 0, 0, 1);
        
        builder.rect(
                0.5f, -0.5f, 0,
               -0.5f, -0.5f, 0,
               -0.5f,  0.5f, 0,
                0.5f,  0.5f, 0,
                0, 0, -1
       );
        model = modelBuilder.end();
        
        modelInstance = new ModelInstance(model);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
    }
    

    @Override
    public void render () {
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            isPaused = !isPaused; 
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            useOrtho = !useOrtho; 
            if (useOrtho) {
                orthoCamera.update();
                batch.setProjectionMatrix(orthoCamera.combined);
            } else {
                perspCamera.update();
                batch.setProjectionMatrix(perspCamera.combined);
            }
        }
        
        ScreenUtils.clear(0, 0, 0, 1);
        
        float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);
        
        if (!isPaused) {
            update(delta); 
        }
        
        if(useOrtho)
        	doRender2D() ;
        else
        	doRender3D() ; 
    }

    public void update(float delta) {
    	// LE 2D
        distanceLent += 1 * delta;
        distanceRapide += 1 * delta * 5;
        
        // LE 3D
        modelInstance.transform.rotate(Vector3.Y, 90 * delta);
        
    }

    public void doRender2D() {
        batch.begin();
        
        batch.draw(img, distanceRapide, 0, sizeImg, sizeImg);
        batch.draw(img, distanceLent, sizeImg, sizeImg, sizeImg);
        
        batch.end();
    }
    
    public void doRender3D() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(perspCamera);
        modelBatch.render(modelInstance);
        modelBatch.end();
    }
    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
