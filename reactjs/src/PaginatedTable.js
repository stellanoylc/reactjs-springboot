import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchPaginatedData } from "./Redux/dataSlice";

import Table from 'react-bootstrap/Table';

const PaginatedTable = () => {
    const dispatch = useDispatch();

    // Access Redux state
    const { records, currentPage, totalPages, pageSize, loading, error } = useSelector(
        (state) => state.data
    );

    // Fetch data for the current page
    useEffect(() => {
        dispatch(fetchPaginatedData({ page: currentPage, size: pageSize }));
    }, [dispatch, currentPage, pageSize]);

    // Handle page change
    const handlePageChange = (page) => {
        if (page >= 0 && page < totalPages) {
            dispatch(fetchPaginatedData({ page, size: pageSize }));
        }
    };

    return (
        <div>
            <h2>Saved Data Table</h2>

            {loading && <p>Loading...</p>}
            {error && <p style={{ color: "red" }}>Error: {error}</p>}

            {!loading && records.length > 0 && (
                <>
                    {/* Table to display data */}
                    <Table striped bordered hover size="sm" border="1" style={{ width: "100%", marginTop: "20px" }}>
                        <thead>
                            <tr>
                                <th>ID</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Location</th>
                            </tr>
                        </thead>
                        <tbody>
                            {records.map((record) => (
                                <tr key={record.id}>
                                    <td>{record.id}</td>
                                    <td>{record.name}</td>
                                    <td>{record.address}</td>
                                    <td>{record.location}</td>   
                                </tr>
                            ))}
                        </tbody>
                    </Table>

                    {/* Pagination Controls */}
                    <div style={{ marginTop: "10px" }}>
                        <button
                            onClick={() => handlePageChange(currentPage - 1)}
                            disabled={currentPage === 0}
                            style={{ margin: "0 5px" }}
                        >
                            Previous
                        </button>
                        {Array.from({ length: totalPages }, (_, index) => (
                            <button
                                key={index}
                                onClick={() => handlePageChange(index)}
                                disabled={currentPage === index}
                                style={{
                                    margin: "0 5px",
                                    padding: "5px 10px",
                                    backgroundColor: currentPage === index ? "#007bff" : "#f1f1f1",
                                    color: currentPage === index ? "#fff" : "#000",
                                    border: "none",
                                    cursor: "pointer",
                                }}
                            >
                                {index + 1}
                            </button>
                        ))}
                        <button
                            onClick={() => handlePageChange(currentPage + 1)}
                            disabled={currentPage === totalPages - 1}
                            style={{ margin: "0 5px" }}
                        >
                            Next
                        </button>
                    </div>
                </>
            )}
        </div>
    );
};

export default PaginatedTable;