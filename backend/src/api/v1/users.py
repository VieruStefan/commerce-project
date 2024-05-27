from fastapi import APIRouter
import database
from models.user import UserRegister 
router = APIRouter(prefix="/users", tags=["Users"])

@router.get("/", summary="users")
def get_users():
    users = database.get_users()
    return users

@router.get("/{id}", summary="user")
def get_user(id: int):
    user = database.get_user(id)
    return f"Hello user {id}"

@router.post("/", summary="user")
def post_user(userRegister: UserRegister):
    user = database.create_user(userRegister)
    return f"Hello user "