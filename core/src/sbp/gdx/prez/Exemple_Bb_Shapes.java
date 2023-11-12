package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

public class Exemple_Bb_Shapes extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED); // Set the color of the rectangle

        // Draw the rectangle
        // Parameters: x, y, width, height
        shapeRenderer.rect(50, 50, 100, 100);

        shapeRenderer.setColor(0,0,1,.5f);
        shapeRenderer.rect(100, 100, 100, 100);
        
        // End ShapeRenderer
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        // Dispose of the ShapeRenderer when done
        shapeRenderer.dispose();
    }
}
