package com.shade.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Water extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture tex;
	private Sprite sprite, background;
	private OrthographicCamera camera;
	
	private ShaderProgram shader, def;
	
	
	/*
	* creates or initializes all base objects and instance variables
	*/
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		tex = new Texture(Gdx.files.internal("water.png"));
		
		sprite = new Sprite(tex);
		sprite.setBounds(0, -Gdx.graphics.getHeight() / 1.5f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background = new Sprite(new Texture("back.png"));
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/colorShading.vert"), Gdx.files.internal("shaders/colorShading.frag"));
		
		
		 if (shader.getLog().length()!=0)
		        System.out.println(shader.getLog());

		
		batch = new SpriteBatch();
		def = batch.createDefaultShader();
	}

	float elapsedTime = 0;
	
	/*
	* starts the gameloop
	*/
	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		elapsedTime += Gdx.graphics.getDeltaTime() * 1;
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		batch.begin();
		
		
		batch.setShader(def);
		background.draw(batch);
		
		
		batch.setShader(shader);
		shader.setUniformf(shader.getUniformLocation("time"), elapsedTime);
		
		
		sprite.draw(batch);
		
		
		batch.end();
	}
}
