package test;

import static org.junit.Assert.*;
import model.Nave;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import config.Configuracoes;

public class NaveTest {

	Nave nave;
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
		nave = new Nave();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMover() {
		nave.setDx(-2);
		nave.setX(0);
		nave.mover();
		assertEquals(1, nave.getX());
		
		nave.setDx(2);
		nave.setX(800);
		nave.mover();
		assertEquals((int) (Configuracoes.getInstance().getLargura()*0.9), nave.getX());
	}

}
