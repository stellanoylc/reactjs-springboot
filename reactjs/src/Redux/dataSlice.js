import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

// Async thunk to fetch data from the API
//result
const API_URL_PAGINATION ='http://localhost:8080/api2/allGoogleLocation';

export const fetchPaginatedData = createAsyncThunk("data/fetchPaginatedData", async ({ page, size })  => {
   
    const url = API_URL_PAGINATION +'?page='+ page + '&size='+size;
     console.log("page=",page, '- SIXE=',size, "url=",url);
    const response = await axios.get(url);    
    console.log("response.data=",response.data);   

    return response.data; // Assuming the API returns an array of records
});

// Slice definition
const dataSlice = createSlice({
    name: "data",
    initialState: {
        records: [],
        currentPage: 0,
        totalPages: 0,
        pageSize: 10,
        loading: false,
        error: null,
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(fetchPaginatedData.pending, (state) => {
                state.loading = true;
            })
            .addCase(fetchPaginatedData.fulfilled, (state, action) => {
                state.loading = false;
                state.records = action.payload.content;
                state.currentPage = action.payload.number;
                state.totalPages = action.payload.totalPages;
                state.pageSize = action.payload.size;
            })
            .addCase(fetchPaginatedData.rejected, (state, action) => {
                state.loading = false;
                state.error = action.error.message;
            });
    },
});


export default dataSlice.reducer;