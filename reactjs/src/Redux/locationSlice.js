import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';


export const saveAddress = createAsyncThunk(
  'data/saveAddress',
  async (payload, { rejectWithValue }) => {
    try {
      console.log("payload=",payload);
      const data = {
        name: payload.name,
        address: payload.address,
        location: JSON.stringify(payload.location)
        
    };
      const response = await axios.post('http://localhost:8080/api2/googleLocations', data
        , {
            headers: {
                "Content-Type": "application/json", // Specify JSON content type
            },
        }
        );
      console.log(response.data);
      return response.data; // Assuming the API returns the saved data
      
    } catch (error) {
      return rejectWithValue(error.response.data || error.message);
    }
  }
);



const locationSlice = createSlice({
  name: 'location',
  initialState: {
    selectedPlace: null,
	mapCenter: { lat: 37.7749, lng: -122.4194 },
  //api
  status: 'idle', // idle | loading | succeeded | failed
    error: null, 
  },
  reducers: {
    setSelectedPlace: (state, action) => {
      state.selectedPlace = action.payload;
	  state.mapCenter = action.payload.location; 
    },
   },
  extraReducers: (builder) => {
    builder
      .addCase(saveAddress.pending, (state) => {
        state.status = 'loading';
        state.error = null;
      })
      .addCase(saveAddress.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.data = action.payload;
      })
      .addCase(saveAddress.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.payload;
      });
  },
});

export const { setSelectedPlace } = locationSlice.actions;
export default locationSlice.reducer;