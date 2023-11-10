package sbp.gdx.prez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Exemple_Hh_Gravity_Avec_Fixture extends ApplicationAdapter {
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private World world;
    private Body boxBody1, boxBody2, groundBody;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(30, 30 * (h / w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        // First Box
        BodyDef bodyDef1 = new BodyDef();
        bodyDef1.type = BodyDef.BodyType.DynamicBody;
        bodyDef1.position.set(15, 20); // Adjusted position

        boxBody1 = world.createBody(bodyDef1);

        PolygonShape boxShape1 = new PolygonShape();
        boxShape1.setAsBox(1, 1);
        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = boxShape1;
        fixtureDef1.density = 1.0f; // Set a positive density
        fixtureDef1.friction = 0.5f; // Optional: Adjust friction
        fixtureDef1.restitution = 0.5f; // Optional: Adjust restitution (bounciness)
        boxBody1.createFixture(fixtureDef1);

        // Second Box
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.DynamicBody;
        bodyDef2.position.set(14, 24); // Position above the first box

        boxBody2 = world.createBody(bodyDef2);

        PolygonShape boxShape2 = new PolygonShape();
        boxShape2.setAsBox(1, 1);
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = boxShape2;
        fixtureDef2.density = 1.0f;
        fixtureDef2.friction = 0.5f;
        fixtureDef2.restitution = 0.5f;
        boxBody2.createFixture(fixtureDef2);

        // Ground
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(15, 10));
        groundBodyDef.type = BodyDef.BodyType.StaticBody;

        groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth, 1.0f);

        groundBody.createFixture(groundBox, 0.0f);

        // Dispose of shapes
        boxShape1.dispose();
        boxShape2.dispose();
        groundBox.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 6, 2);

        camera.update();
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }
}
