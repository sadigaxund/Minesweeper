/***************************************************************************
 *     Copyright 2021 Sadig Akhund @ https://github.com/sadigaxund
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 **************************************************************************/

package Datas;

import Components.BadInputException;
import Util.Tools;

/**
 * Data class for holding source of images
 *
 */
public interface Images {

    static final String ROOT = ".//img";
    static final String FORMAT = ".png";

    public static final String WIN_SCREEN = ROOT + "//win_icon" + FORMAT;
    public static final String LOSE_SCREEN = ROOT + "//lose_icon" + FORMAT;

    public static final String MINE = ROOT + "//tiles//mine1" + FORMAT;
    public static final String MINE_BLOWN = ROOT + "//tiles//mine2" + FORMAT;
    public static final String MINE_NOT = ROOT + "//tiles//mine3" + FORMAT;

    public static final String SMILE_OOO = ROOT + "//smile//3" + FORMAT;
    public static final String SMILE_DEAD = ROOT + "//smile//2" + FORMAT;
    public static final String SMILE_COOL = ROOT + "//smile//1" + FORMAT;
    public static final String SMILE_HAPPY = ROOT + "//smile//4" + FORMAT;
    public static final String SMILE_HAPPY_PRESS = ROOT + "//smile//5" + FORMAT;

    public static final String TILE_NOT_PRESSED = ROOT + "//tiles//tile1" + FORMAT;
    public static final String TILE_PRESSED = ROOT + "//tiles//tile2" + FORMAT;
    public static final String FLAG = ROOT + "//tiles//flag" + FORMAT;

    public static String getHexDigit(int num) {
	if (!Tools.isDigit(num))
	    throw new BadInputException("Entered number must be in range [0; 9]");

	return ROOT + "//hex//" + num + FORMAT;
    }

    public static String getTileDigit(int num) {
	if (!Tools.isDigit(num) && num != 9)
	    throw new BadInputException("Entered number must be in range [0; 8]");

	return ROOT + "//tiles//" + num + FORMAT;
    }

}
