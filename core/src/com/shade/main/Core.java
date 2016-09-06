package com.shade.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Core extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture tex;
	private Sprite sprite;
	private OrthographicCamera camera;
	
	private ShaderProgram shader;
	
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		tex = new Texture(Gdx.files.internal("bgloading.png"));
		
		sprite = new Sprite(tex);
		sprite.setBounds(100, 100, Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight() / 1.5f);
		
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/colorShading.vert"), Gdx.files.internal("shaders/colorShading.frag"));
		
		
		 if (shader.getLog().length()!=0)
		        System.out.println(shader.getLog());

		
		batch = new SpriteBatch();
	}

	float elapsedTime = 0;
	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		elapsedTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.setShader(shader);
		shader.setUniformf(shader.getUniformLocation("time"), elapsedTime);
		sprite.draw(batch);
		batch.end();
	}
}
