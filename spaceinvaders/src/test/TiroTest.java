package test;

import static org.junit.Assert.*;
import model.Tiro;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import config.Configuracoes;

public class TiroTest {

	Tiro tiro;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Configuracoes.getInstance().setResolucao(1);
		Configuracoes.getInstance().setAltura(600);
		Configuracoes.getInstance().setLargura(800);
		tiro = new Tiro(50,50);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMover() {
		tiro.setVelocidade(3);
		tiro.setY(0);
		tiro.mover();
		assertFalse(tiro.isVisible());

		tiro.setY(600);
		tiro.mover();
		assertFalse(tiro.isVisible());
	}

}
