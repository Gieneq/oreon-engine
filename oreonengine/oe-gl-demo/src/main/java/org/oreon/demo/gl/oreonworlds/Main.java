package org.oreon.demo.gl.oreonworlds;

import org.oreon.core.gl.scene.GLCamera;
import org.oreon.core.system.CoreEngine;
import org.oreon.core.system.CoreSystem;
import org.oreon.core.system.GLFWInput;
import org.oreon.core.system.Window;
import org.oreon.demo.gl.oreonworlds.assets.plants.Palm01ClusterGroup;
import org.oreon.demo.gl.oreonworlds.assets.plants.Tree01ClusterGroup;
import org.oreon.demo.gl.oreonworlds.assets.plants.Tree02ClusterGroup;
import org.oreon.demo.gl.oreonworlds.assets.rocks.Rock01ClusterGroup;
import org.oreon.demo.gl.oreonworlds.assets.rocks.Rock02ClusterGroup;
import org.oreon.demo.gl.oreonworlds.gui.GUI;
import org.oreon.demo.gl.oreonworlds.shaders.TerrainGridShader;
import org.oreon.demo.gl.oreonworlds.shaders.TerrainShader;
import org.oreon.demo.gl.oreonworlds.water.Ocean;
import org.oreon.modules.gl.atmosphere.SkySphere;
import org.oreon.modules.gl.atmosphere.Sun;
import org.oreon.modules.gl.terrain.GLTerrain;
import org.oreon.system.gl.desktop.GLRenderEngine;
import org.oreon.system.gl.desktop.GLWindow;

public class Main {

	public static void main(String[] args) {

		CoreEngine coreEngine = new CoreEngine();
		CoreSystem coreSystem = CoreSystem.getInstance();
		GLRenderEngine renderEngine = new GLRenderEngine();
		Window window = new GLWindow();
		GLFWInput input = new GLFWInput();
		
		renderEngine.setGui(new GUI());
		window.setWidth(1280);
		window.setHeight(720);
		window.setTitle("OREON ENGINE oreonworlds 2.0");
		
		coreSystem.setRenderEngine(renderEngine);
		coreSystem.setWindow(window);
		coreSystem.setInput(input);
		coreSystem.getScenegraph().setCamera(new GLCamera(input));

		coreEngine.init(coreSystem);
		
		GLTerrain terrain = new GLTerrain();
		terrain.init("oreonworlds/terrain/terrain_settings.txt",
				   "oreonworlds/terrain/terrain_settings_LowPoly.txt",
				   TerrainShader.getInstance(),
				   TerrainGridShader.getInstance(), 
				   null);
		coreSystem.getScenegraph().setTerrain(terrain);
		
		coreSystem.getScenegraph().addObject(new SkySphere());	
		coreSystem.getScenegraph().addTransparentObject(new Sun());
		coreSystem.getScenegraph().setWater(new Ocean());
		
		coreSystem.getScenegraph().getRoot().addChild(new Palm01ClusterGroup());
		coreSystem.getScenegraph().getRoot().addChild(new Tree02ClusterGroup());
		coreSystem.getScenegraph().getRoot().addChild(new Tree01ClusterGroup());
		coreSystem.getScenegraph().getRoot().addChild(new Rock01ClusterGroup());
		coreSystem.getScenegraph().getRoot().addChild(new Rock02ClusterGroup());
		
		coreEngine.start();
	}

}
