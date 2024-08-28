package com.soptie.server.temporary.data;

public class ThemeData {

	public static String getColor(long id) {
		return switch ((int)id) {
			case 1 -> "#E19098";
			case 2 -> "#38B662";
			case 3 -> "#6392D8";
			case 4 -> "#EF596F";
			case 5 -> "#52A1DA";
			case 6 -> "#EDBF4F";
			default -> "#EB9D07";
		};
	}

	public static String getHappinessBackgroundUrl(long id) {
		return switch ((int)id) {
			case 1 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/happiness/card/img_happycard_1ver2@3x.png?raw=true";
			case 2 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/happiness/card/img_happycard_3ver2@3x.png?raw=true";
			case 3 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/happiness/card/img_happycard_4ver2@3x.png?raw=true";
			case 4 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/happiness/card/img_happycard_5ver2@3x.png?raw=true";
			case 5 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/happiness/card/img_happycard_6ver2@3x.png?raw=true";
			case 6 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/happiness/card/img_happycard_7ver2@3x.png?raw=true";
			default ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/happiness/card/img_happycard_2ver2@3x.png?raw=true";
		};
	}

	public static String getIconImageUrl(long id) {
		return switch ((int)id) {
			case 1 ->
				"https://softie-data-image.s3.ap-northeast-2.amazonaws.com/icon/%E1%84%80%E1%85%AA%E1%86%AB%E1%84%80%E1%85%A8_%E1%84%8A%E1%85%A1%E1%87%82%E1%84%80%E1%85%B5.png";
			case 2 ->
				"https://softie-data-image.s3.ap-northeast-2.amazonaws.com/icon/%E1%84%92%E1%85%A1%E1%86%AB_%E1%84%80%E1%85%A5%E1%86%AF%E1%84%8B%E1%85%B3%E1%86%B7_%E1%84%89%E1%85%A5%E1%86%BC%E1%84%8C%E1%85%A1%E1%86%BC.png";
			case 3 ->
				"https://softie-data-image.s3.ap-northeast-2.amazonaws.com/icon/%E1%84%82%E1%85%A1%E1%84%8B%E1%85%AA_%E1%84%8E%E1%85%B5%E1%86%AB%E1%84%92%E1%85%A2%E1%84%8C%E1%85%B5%E1%84%80%E1%85%B5.png";
			case 4 ->
				"https://softie-data-image.s3.ap-northeast-2.amazonaws.com/icon/%E1%84%86%E1%85%A1%E1%84%8B%E1%85%B3%E1%86%B7_%E1%84%8E%E1%85%A2%E1%86%BC%E1%84%80%E1%85%B5%E1%86%B7.png";
			case 5 ->
				"https://softie-data-image.s3.ap-northeast-2.amazonaws.com/icon/%E1%84%80%E1%85%A5%E1%86%AB%E1%84%80%E1%85%A1%E1%86%BC%E1%84%92%E1%85%A1%E1%86%AB_%E1%84%86%E1%85%A9%E1%86%B7.png";
			case 6 ->
				"https://softie-data-image.s3.ap-northeast-2.amazonaws.com/icon/%E1%84%90%E1%85%A9%E1%86%BC%E1%84%90%E1%85%A9%E1%86%BC%E1%84%92%E1%85%A1%E1%86%AB_%E1%84%90%E1%85%A9%E1%86%BC%E1%84%8C%E1%85%A1%E1%86%BC.png";
			default ->
				"https://softie-data-image.s3.ap-northeast-2.amazonaws.com/icon/%E1%84%89%E1%85%A1%E1%86%AB%E1%84%84%E1%85%B3%E1%86%BA%E1%84%92%E1%85%A1%E1%86%AB_%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%89%E1%85%A1%E1%86%BC.png";
		};
	}

	public static String getDailyBackgroundImageUrl(long id) {
		return switch ((int)id) {
			case 1 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/routine/daily/small_kindness/background.png?raw=true";
			case 2 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/routine/daily/overcome_helplessness/background.png?raw=true";
			case 3 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/routine/daily/restful_sleep/background.png?raw=true";
			case 4 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/routine/daily/environmental_guard/background.png?raw=true";
			case 5 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/routine/daily/healthly_body/background.png?raw=true";
			case 6 ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/routine/daily/chubby_bank_account/background.png?raw=true";
			default ->
				"https://github.com/Team-Sopetit/sopetit-image/blob/main/routine/daily/fresh_start/background.png?raw=true";
		};
	}
}
