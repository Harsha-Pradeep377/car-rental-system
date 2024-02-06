package lk.ijse.carrental.service.custom.impl;

import lk.ijse.carrental.dao.DaoFactory;
import lk.ijse.carrental.dao.DaoType;
import lk.ijse.carrental.dao.custom.BookingDao;
import lk.ijse.carrental.dao.custom.CarDao;
import lk.ijse.carrental.dao.custom.CustomerDao;
import lk.ijse.carrental.dto.BookingDto;
import lk.ijse.carrental.dto.CarDto;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.entity.CarEntity;
import lk.ijse.carrental.entity.CategoryEntity;
import lk.ijse.carrental.entity.CustomerEntity;
import lk.ijse.carrental.service.custom.BookingService;

import java.util.ArrayList;
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
        BookingEntity bookingEntity = bookingDao.search(bookingId);
        var bookingDto = new BookingDto(
                bookingEntity.getId(),
                bookingEntity.getCustomerEntity().getId(),
                bookingEntity.getCarEntity().getId(),
                bookingEntity.getBookDate(),
                bookingEntity.getReturnDate(),
                bookingEntity.getRate(),
                bookingEntity.getTotal(),
                bookingEntity.getAdvance(),
                bookingEntity.getBalance());
        return bookingDto;
    }

    @Override
    public List<BookingDto> getAll() {
        List<BookingDto> bookingDtos = new ArrayList<>();
        List<BookingEntity> bookingEntities = bookingDao.getAll();
        for (BookingEntity booking : bookingEntities) {
            var bookingDto = new BookingDto(booking.getId(),booking.getCustomerEntity().getId(),booking.getCarEntity().getId(),booking.getBookDate(),booking.getReturnDate(), booking.getRate(),booking.getTotal(),booking.getAdvance(),booking.getBalance());
            bookingDtos.add(bookingDto);
        }
        return bookingDtos;
    }

    @Override
    public void updateBooking(BookingDto bookingDto) {
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

        bookingDao.update(bookingEntity);

    }

    @Override
    public void deleteBooking(BookingDto dto) {
        CustomerEntity customerEntity = customerDao.search(dto.getCustId());
        CarEntity carEntity = carDao.search(dto.getCarId());
        var bookingEntity = new BookingEntity(
                dto.getId(),
                customerEntity,
                carEntity,
                dto.getBookDate(),
                dto.getReturnDate(),
                dto.getRate(),
                dto.getTotal(),
                dto.getAdvance(),
                dto.getBalance());
        bookingDao.delete(bookingEntity);
    }
}
