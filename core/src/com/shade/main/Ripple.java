package com.shade.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;

public class Ripple extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture tex;
	private Sprite sprite, background, s;
	private OrthographicCamera camera;

	private ShaderProgram shader, fore, def;

	/*
	* initialize all objects and instance variables
	*/
	@Override
	public void create() {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch = new SpriteBatch();
		def = batch.createDefaultShader();

		sprite = new Sprite(new Texture(Gdx.files.internal("purple.jpg")));
		sprite.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 200);
		sprite.getTexture().bind(0);

		background = new Sprite(new Texture(Gdx.files.internal("background.png")));
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.getTexture().bind(1);

		s = new Sprite(new Texture(Gdx.files.internal("water.png")));
		s.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 2);

		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/ripple.vert"), Gdx.files.internal("shaders/ripple.frag"));
		fore = new ShaderProgram(Gdx.files.internal("shaders/ripple.vert"), Gdx.files.internal("shaders/foreground.frag"));

		s.getTexture().bind(2);
		sprite.getTexture().bind(0);

		if (shader.getLog().length() != 0)
			System.out.println(shader.getLog());

	}

	float elapsedTime = 0;
	
	//start render loop
	@Override
	public void render() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		elapsedTime += Gdx.graphics.getDeltaTime() * 1000;
		float dt = Gdx.graphics.getDeltaTime();
		elapsedTime += dt;
		float angle = elapsedTime * (2 * MathUtils.PI);
		if (angle > (2 * MathUtils.PI))
			angle -= (2 * MathUtils.PI);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    Gdx.gl20.glEnable(GL20.GL_BLEND);   

		batch.begin();
		batch.setShader(shader);
		// background.draw(batch);

		batch.setShader(fore);
		fore.setUniformi(fore.getUniformLocation("u_tex0"), 0);
		fore.setUniformi(fore.getUniformLocation("u_tex1"), 2);
		fore.setUniformf(fore.getUniformLocation("alpha"), .75f);
		fore.setUniformf(fore.getUniformLocation("timedelta"), elapsedTime);
		sprite.draw(batch);
		s.draw(batch);
		System.out.println(shader.getLog());

		batch.end();
	}
}
