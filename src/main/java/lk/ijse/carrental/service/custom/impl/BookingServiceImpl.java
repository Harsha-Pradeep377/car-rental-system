package lk.ijse.carrental.service.custom.impl;

import lk.ijse.carrental.dao.DaoFactory;
import lk.ijse.carrental.dao.DaoType;
import lk.ijse.carrental.dao.custom.BookingDao;
import lk.ijse.carrental.dao.custom.CarDao;
import lk.ijse.carrental.dao.custom.CustomerDao;
import lk.ijse.carrental.dto.BookingDto;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.entity.CarEntity;
import lk.ijse.carrental.entity.CustomerEntity;
import lk.ijse.carrental.service.custom.BookingService;

import java.util.List;

public class BookingServiceImpl implements BookingService {
    private BookingDao bookingDao = DaoFactory.getDao(DaoType.BOOKING);
    private CustomerDao customerDao = DaoFactory.getDao(DaoType.CUSTOMER);
    private CarDao carDao = DaoFactory.getDao(DaoType.CAR);
    @Override
    public void saveBooking(BookingDto bookingDto) {
        CustomerEntity customerEntity = customerDao.search(bookingDto.getCustId());
        CarEntity carEntity = carDao.search(bookingDto.getCarId());

        var bookingEntity = new BookingEntity(
                bookingDto.getId(),
                customerEntity,
                carEntity,
                bookingDto.getBookDate(),
                bookingDto.getReturnDate(),
                bookingDto.getRate(),
                bookingDto.getTotal(),
                bookingDto.getAdvance(),
                bookingDto.getBalance());

        bookingDao.save(bookingEntity);
    }

    @Override
    public BookingDto search(String bookingId) {
        return null;
    }

    @Override
    public List<BookingDto> getAll() {
        return null;
    }

    @Override
    public void updateBooking(BookingDto bookingDtoDto) {

    }

    @Override
    public void deleteBooking(BookingDto dto) {

    }
}
