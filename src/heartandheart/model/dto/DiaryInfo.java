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
	private int emotionNo;
	private int weatherNo;
	private String reportingDate;
	private int sleepingTime;
	private String diaryComment;
	private int isPublic;
}
