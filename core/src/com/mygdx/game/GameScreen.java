package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {
    final BlackjackGame game;

    private OrthographicCamera camera;
    //private Texture cardImage;

    private Array<Array<Texture>> cardTextures;

    private Sprite card;

    public GameScreen(final BlackjackGame _game) {
        game = _game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        //cardImage = new Texture(Gdx.files.internal("c12.png"));
        cardTextures = new Array<Array<Texture>>(4);
        String[] suits = {"spades", "hearts", "clubs", "diamonds"};
        for(int j = 0; j < 4; j++) {
            //this will grow this array from the first dimension onwards (implicit dimension)
            //from the first index onwards
            cardTextures.add(new Array<Texture>(13));
            for(int i = 1; i <= 13; i++) {
                String filename = suits[j].charAt(0) + String.format("%02d", i) + ".png";
                cardTextures.get(j).add(new Texture(Gdx.files.internal(filename)));
            }
        }
        card = new Sprite(cardTextures.get(3).get(5));
        float scale = 150 / card.getWidth();
        card.setSize(150, card.getHeight() * scale);
        card.setPosition(0, 0);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Reset the screen
        Gdx.gl.glClearColor(0, 0.8f, 0, 0.2f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        card.draw(game.batch);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 13; i++) {
                cardTextures.get(j).get(i).dispose();
            }
        }
    }
}
