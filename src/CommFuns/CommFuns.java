package CommFuns;

import Model.TipModel;

public class CommFuns {
	public static String getTip(Boolean success, String msg, String actions) {
		TipModel tip = new TipModel(success, msg, actions);
		return tip.ToJSON();
	}
}
