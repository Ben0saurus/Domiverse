package main;

import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;

    }

    public void setObject() {

        //CHESTS
        gp.obj[0] = new OBJ_Chest();
        gp.obj[0].worldX = 39 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        //KEYS
        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 2 * gp.tileSize;
        gp.obj[1].worldY = 2 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 27 * gp.tileSize;
        gp.obj[2].worldY = 21 * gp.tileSize;

        gp.obj[3] = new OBJ_Key();
        gp.obj[3].worldX = 37 * gp.tileSize;
        gp.obj[3].worldY = 43 * gp.tileSize;

        //DOORS
        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 2 * gp.tileSize;
        gp.obj[4].worldY = 24 * gp.tileSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 6 * gp.tileSize;
        gp.obj[5].worldY = 24 * gp.tileSize;

        gp.obj[6] = new OBJ_Door();
        gp.obj[6].worldX = 36 * gp.tileSize;
        gp.obj[6].worldY = 6 * gp.tileSize;

        //DRUGS
        gp.obj[7] = new OBJ_Coke();
        gp.obj[7].worldX = 2 * gp.tileSize;
        gp.obj[7].worldY = 18 * gp.tileSize;

        gp.obj[8] = new OBJ_Heroine();
        gp.obj[8].worldX = 2 * gp.tileSize;
        gp.obj[8].worldY = 22 * gp.tileSize;

        gp.obj[9] = new OBJ_Vape();
        gp.obj[9].worldX = 6 * gp.tileSize;
        gp.obj[9].worldY = 22 * gp.tileSize;

        gp.obj[10] = new OBJ_Cigarette();
        gp.obj[10].worldX = 33 * gp.tileSize;
        gp.obj[10].worldY = 6 * gp.tileSize;


    }

}
