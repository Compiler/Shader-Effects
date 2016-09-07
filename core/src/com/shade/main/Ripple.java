package com.shade.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Ripple extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture tex;
	private Sprite sprite;
	private OrthographicCamera camera;
	
	private ShaderProgram shader;
	
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		sprite = new Sprite(new Texture(Gdx.files.internal("purple.jpg")));
		sprite.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/ripple.vert"), Gdx.files.internal("shaders/ripple.frag"));
		sprite.getTexture().bind(0);
		
		 if (shader.getLog().length()!=0)
		        System.out.println(shader.getLog());

		batch = new SpriteBatch();
	}

	float elapsedTime = 0;
	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		elapsedTime += Gdx.graphics.getDeltaTime() * 1;
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		batch.begin();
		
		
		batch.setShader(shader);
		shader.setUniformf(shader.getUniformLocation("time"), elapsedTime);
		shader.setUniform2fv(shader.getUniformLocation("resolution"), 
				new float[]{Gdx.graphics.getWidth(), Gdx.graphics.getHeight()},  0, 2);
		
		shader.setUniformi(shader.getUniformLocation("tex"), 0);
		sprite.draw(batch);
		System.out.println(shader.getLog());
		
		
		batch.end();
	}
}
