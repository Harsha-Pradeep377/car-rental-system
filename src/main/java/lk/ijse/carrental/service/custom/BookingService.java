package lk.ijse.carrental.service.custom;

import lk.ijse.carrental.dto.BookingDto;
import lk.ijse.carrental.dto.CategoryDto;

import java.util.List;

public interface BookingService {
    void saveBooking(BookingDto bookingDto);

    BookingDto search(String bookingId);

    List<BookingDto> getAll();

    void updateBooking(BookingDto bookingDtoDto);

    void deleteBooking(BookingDto dto);
}
