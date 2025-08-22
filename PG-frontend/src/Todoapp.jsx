import {useState} from 'react';
import { v4 as uuidv4 } from "uuid";

function Task(){
    let [Tasks,setTasks]=useState([{index:uuidv4(),name:"sample task"}]);
    let [newTask,setNewTask]=useState({index:"",name:""});

    function deleteTask(id){
   let copy=Tasks.filter(task=>task.index!=id);
   setTasks(copy);
}
    return(
 <> 
 <h3>Hi! This is counter</h3>
   <input value={newTask.name} onChange={(e)=>{setNewTask({index:uuidv4(),name:e.target.value})}}/>
    <button onClick={()=>{setTasks([...Tasks,newTask])}}>Add Task</button>
   <ul>
      {
        Tasks.map(task=><li key={task.index}>
         {task.name}
         <button onClick={()=>deleteTask(task.index)}>delete</button>
         </li>)
      }
   </ul>
 </> 
   
)};
export default Task;