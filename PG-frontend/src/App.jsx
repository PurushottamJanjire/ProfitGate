import { useState } from 'react'
import reactLogo from './assets/react.svg'
import Task from './Todoapp'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
     <Task/>
     
    </>
  )
}

export default App
