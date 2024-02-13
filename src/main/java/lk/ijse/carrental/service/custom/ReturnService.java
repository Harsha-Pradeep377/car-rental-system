package lk.ijse.carrental.service.custom;

import lk.ijse.carrental.dto.BookingDto;
import lk.ijse.carrental.dto.ReturnDto;

import java.util.List;

public interface ReturnService {
    void saveReturnDetails(ReturnDto returnDto);

    ReturnDto search(String returnId);

    List<ReturnDto> getAll();
    void deleteReturnDetails(ReturnDto dto);
}
