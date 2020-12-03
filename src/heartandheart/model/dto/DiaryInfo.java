package heartandheart.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryInfo {
	private int diaryNo;
	private String userInfo;
	private String emotionNo;
	private String reportingDate;
	private String sleepingTime;
	private String diaryComment;
	private String isPublic;
	private String Code;

	
}
