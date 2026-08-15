package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class NursingElderDto extends BaseDto {
    private Long id;

    private List<Long> nursingIds;

    @NotNull(message = "老人ID不能为空")
    private Long elderId;
}