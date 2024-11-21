import logo from './logo.svg';
import './App.css';
import AutocompleteSearch from './AutocompleteSearch';
import 'bootstrap/dist/css/bootstrap.min.css';




function App() {
	
  return (
    
       <div style={{ textAlign: 'center', marginTop: '50px' }}>
      <h1>Search a place with Autocomplete</h1>
        <AutocompleteSearch />      
       </div>
    
  );
}

export default App;
