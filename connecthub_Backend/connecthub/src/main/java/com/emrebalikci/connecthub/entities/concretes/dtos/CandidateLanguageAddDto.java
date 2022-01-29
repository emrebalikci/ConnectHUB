package com.emrebalikci.connecthub.entities.concretes.dtos;

import com.emrebalikci.connecthub.core.utilities.Msg;
import com.emrebalikci.connecthub.core.utilities.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateLanguageAddDto {

    @NotNull(message = Msg.Annotation.REQUIRED)
    private Integer candidateId;

    @NotNull(message = Msg.Annotation.REQUIRED)
    private Short languageId;

    @NotNull(message = Msg.Annotation.REQUIRED)
    @Pattern(regexp = Utils.Const.LANG_LVL_REGEXP, message = Msg.Annotation.PATTERN)
    private String languageLevel;

}
