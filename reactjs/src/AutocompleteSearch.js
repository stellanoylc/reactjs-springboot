import React, { useRef, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setSelectedPlace, saveAddress } from './Redux/locationSlice';
import { fetchPaginatedData } from "./Redux/dataSlice"; 
import { useJsApiLoader, Autocomplete } from '@react-google-maps/api';

import MapComponent from './MapComponent';

import PaginatedTable from './PaginatedTable';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const libraries = ['places'];

const AutocompleteSearch = () => {
  const dispatch = useDispatch();
  const autocompleteRef = useRef(null);  
  

  const { isLoaded } = useJsApiLoader({
    googleMapsApiKey: 'AIzaSyDS1mosZvc9PNxaXk-Xcd6_EkaoPPs35K8', // Replace with your API key
    libraries: ['places'],
  });

  const handlePlaceChanged = () => {
    const place = autocompleteRef.current.getPlace();
    if (place) {
      dispatch(setSelectedPlace({
        name: place.name,
        address: place.formatted_address,
        location: place.geometry?.location?.toJSON(), // Includes lat/lng
      }));
    }
  };
  
  const selectedPlace = useSelector((state) => state.location.selectedPlace);

  const [loading, setLoading] = useState(false);
   
  const handleSaveAddress = async () => {
	 if (!isLoaded) {
		alert('Google Maps API is not loaded yet. Please wait.');
		return;
	  }
  
 
    if (selectedPlace) {
     
     setLoading(true);
    try {
            await dispatch(saveAddress(selectedPlace));
            console.log("selectedPlace=",selectedPlace);
            dispatch(fetchPaginatedData({ page: 0, size: 10 })); // Trigger table reload
            console.log("done call to reload paging");
        } catch (error) {
            console.error("Error saving data:", error);
        } finally {
            setLoading(false);
            console.log("done reload paging");
            
        }
    }
  };

  if (!isLoaded) return <div>Loading...</div>;

  return (
    <div>

    <Container fluid="md">
      <Row>
        <Col>
           <Autocomplete
            onLoad={(autocomplete) => (autocompleteRef.current = autocomplete)}
            onPlaceChanged={handlePlaceChanged}
          >
            <input
              type="text"
              placeholder="Search location..."
              style={{width: '100%',
                height: '40px',
                padding: '0 10px',
                borderRadius: '5px',
                border: '1px solid #ccc' }}
            />
            </Autocomplete>
        </Col>
      </Row>
      <Row>
        <Col>
          {selectedPlace && (
        <div style={{ marginTop: '20px' }}>
          <h2>Selected Place Details:</h2>
          <p><strong>Name:</strong> {selectedPlace.name}</p>
          <p><strong>Address:</strong> {selectedPlace.address}</p>
      
        </div>
      )}
        </Col>
      </Row>

      <Row>
        <Col>
          <button
            onClick={handleSaveAddress}
            disabled={loading}
            style={{
              marginTop: '10px',
              padding: '10px 20px',
              fontSize: '16px',
              backgroundColor: '#007BFF',
              color: '#fff',
              border: 'none',
              borderRadius: '5px',
              cursor: 'pointer',
            }}
          >{loading ? "Saving..." : "Save Address"}            
          </button>
        </Col>
      </Row>
      <Row>
        <Col>
           <MapComponent/>
        </Col>
      </Row>
    </Container>		
      <br/> <br/>
     <Container>
      <Row className="justify-content-md-center">
        <Col>
          <PaginatedTable/>
        </Col>
        </Row>
    </Container>

  
    </div>
  );
};

export default AutocompleteSearch;