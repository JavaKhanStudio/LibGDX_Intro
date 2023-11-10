package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Exemple_Da_CameraOrtho_MouseClick extends ApplicationAdapter {
    
	SpriteBatch batch;
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    Array<Vector2> clickPositions;
    final float circleRadius = 20f; // Radius of the circles to be drawn

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // Main camera
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.update();

        clickPositions = new Array<>();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 worldClickPosition = new Vector3(screenX, screenY, 0);
                camera.unproject(worldClickPosition);
                clickPositions.add(new Vector2(worldClickPosition.x, worldClickPosition.y));
                return true;
            }
        });
    }

    @Override
    public void render() {
        handleInput();
        
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        doRender();
    }

    private void handleInput() {
    	
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.x += 10;    
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.x -= 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.position.y += 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.y -= 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.G)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            camera.zoom -= 0.02;
        }
    }

    public void doRender() {
        batch.begin();
        // Render sprites or other batched elements here...
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Vector2 pos : clickPositions) {
            shapeRenderer.circle(pos.x, pos.y, circleRadius);
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}
