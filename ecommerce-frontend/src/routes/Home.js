import { useEffect, useState } from 'react';
import Swal from 'sweetalert2';

import Products from '../components/Products';
import NewProduct from '../components/NewProduct';

import '../components/newProduct.css'

export default function Home() {

	// useState is used to save data
	const [allProducts, setAllProducts] = useState([]);

	const [newProduct, setNewProduct] = useState({
	  name: '',
	  description: '',
	  cost: 0,
	  categories: []
	});
  
	const [posting, setPosting] = useState(false);
	const [toDelete, setToDelete] = useState({deleting: false});
	const [invalidInput, setInvalidInput] = useState(false);

	const token = sessionStorage.getItem('token') || '';

	// initialize invalid input state to false
	function triggerInvalidInput(message) {
	  setInvalidInput(message[0].toUpperCase() + message.substring(1));
	  setTimeout( () => {
		setInvalidInput(false);
	  },3000);
	}
  
	// useEffect is used to interact w/ data outside of the react app
	useEffect( () => {
		fetch('http://localhost:9000/api/private/product', 
			{
				headers: {
					'Authorization': `Bearer ${token}`,
					'Accept': '*/*'
				}
			}
		).then( (res) => {
			const result = res.json()
			return result;
		}).then( (data) => {
			setAllProducts(data)
		})
		.catch( (error) => console.log(error.toString()));
	}, [posting, toDelete.deleting]);
  
	const placeholderUrl = 'https://via.placeholder.com/200/e9fff4';
  
	// check if url uses a secured protocol
	function validateUrl(url) {
	  const parsed = new URL(url);
	  return ['https:', 'http:'].includes(parsed.protocol); 
	}
  
	// validate inputs and update newProduct state accordingly
	function handleProductFormModification(event) {
	  const {type, name, value} = event.target;
  
	  type === 'number' ? 
		value.match(/^[0-9]+$/) ?
		  setNewProduct( (prevState) => {
			setInvalidInput(false);
			return {
			  ...prevState,
			  [name]: Number(value)
			}
		  })
		: 
		  triggerInvalidInput('Cost must be a number!')
	  :
		value.match(/.*[<>/\\].*/) ?
		  triggerInvalidInput(`${name} field doesn't accept special characters!`)
		:
		  value.length > 250 ?
			triggerInvalidInput('Maximum input length is 250 characters!')
		  :
			setNewProduct( (prevState) => {
			  setInvalidInput(false);
			  return {
				...prevState,
				[name]: value
			  }
			})
	}
  
	// trigger submit and send a POST request
	function handleSubmitProduct() {
	  setPosting(true);
	}
  
	useEffect( () => {
	  if (posting) {
		fetch('http://localhost:9000/api/private/product', 
			{
				method: 'POST',
				headers: {
					'Accept': 'application/json',
					'Content-type': 'application/json',
					'Authorization': `Bearer ${token}`
				},
				body: JSON.stringify(newProduct)
			}
		)
		.then( (res) => res.json() )
		.then( (data) => {
		  setPosting(false);
		})
		.catch( (error) => {
		  console.log(error.toString());
		});
	  }
	}, [posting]);
  
	// trigger deletion and send a DELETE request
	function handleDeleteProduct(event, id) {
	  event.stopPropagation();
	  Swal.fire({
		title: 'Do you really want to delete this product?',
		text: 'You won\'t be able to revert this!',
		icon: 'warning',
		showCancelButton: true,
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, delete it!'
	  })
	  .then( (result) => {
		if (result.isConfirmed) {
		  setToDelete({deleting: true, productId: id});
		  Swal.fire(
			'Deleted!',
			'The product has been deleted.',
			'success'
		  );
		}
	  });
	}
  
	useEffect( () => {
	  if (toDelete.deleting) {
		fetch(`http://localhost:9000/api/private/product/${toDelete.productId}`, {
			method: 'DELETE',
			headers: {
				'Authorization': `Bearer ${token}`
			}
		})
		.then( () => {
		  setToDelete( {deleting: false} );
		})
		.catch( (error) => {
		  console.log(error.toString());
		});
	  }
	}, [toDelete.deleting]);

	return (
		<main>
			<Products 
				data={allProducts} 
				validateUrl={validateUrl} 
				url={placeholderUrl}
				handleDelete={handleDeleteProduct}
			/>

			<NewProduct 
				newProduct={newProduct}
				handleModification={handleProductFormModification}
				handleSubmit={handleSubmitProduct}
				invalidInput={invalidInput}
			/>
      </main>
	);
}