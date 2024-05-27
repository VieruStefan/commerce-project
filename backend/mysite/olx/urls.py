from django.urls import include, path

from django.views.decorators.csrf import csrf_exempt
from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("<int:id>/", views.details, name='details'),
    path("sellers/", views.sellers, name='seller'),
    path("sellers/<int:id>", views.seller_details, name='seller_details'),
    path("init_db/", views.init, name='initialize'),  
    path("accounts/", include("django.contrib.auth.urls")), # https://docs.djangoproject.com/en/5.0/topics/auth/default/#module-django.contrib.auth.views
    path("accounts/register", views.register, name='register')
]